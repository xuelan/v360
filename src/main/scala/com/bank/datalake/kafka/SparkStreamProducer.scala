package com.bank.datalake.kafka

import java.util.Properties

import com.bank.datalake.util.AvroUtils
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SpStreamingWritingKafka extends App {

  val eventsPerSecond = 10
  private val broker = "localhost:9092"
//  private val SCHEMA_URL = "http://localhost:8081"
  private val topic = "transaction"

  val props = new Properties()
  props.put("bootstrap.servers", broker)
  props.put("message.send.max.retries", "5")
  props.put("acks", "1")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

//  props.put("schema.registry.url" ,  SCHEMA_URL)
  props.put("schema.compatibility" ,  "FULL")

  val conf = new SparkConf()
              .setAppName("Writing transactions data to kafka")
              .setMaster("local[2]")

  conf.set("spark.driver.allowMultipleContexts", "true")

  val ssc = new StreamingContext(conf, Seconds(5))
  val stream = ssc.receiverStream(new TransactionReceiver(eventsPerSecond))

  val recordsRDD =  stream

  recordsRDD.foreachRDD(rdd=>{

    rdd.foreachPartition( partition =>{
      partition.map( data =>{
        val record = data
        record
      }).foreach( record => {
          val producer: KafkaProducer[String, String] = new KafkaProducer[String, String](props)

          val recordProducer = new ProducerRecord[String, String](topic, record.toString)

          println(s"Send recordProducer ===> ${recordProducer}")
          producer.send(recordProducer)
        })
    })

    val count = rdd.count()
    println(s"Count ===> ${count}")
  })

  ssc.start()
  ssc.awaitTermination()


}