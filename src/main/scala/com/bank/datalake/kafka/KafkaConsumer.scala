package com.bank.datalake.kafka

import java.util.{Properties, _}
import java.util.concurrent.Executors

import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

object KafkaConsumer extends App {
  private val broker = "0.0.0.0:9092"
  //  private val SCHEMA_URL = "http://localhost:8081"
  private val topic = "transaction"

  val props = new Properties()

  props.put("bootstrap.servers", broker)
  props.put("enable.auto.commit", "true")
  props.put("auto.commit.interval.ms", "1000")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

  val consumerKafka = new KafkaConsumer[String, GenericRecord](props)

  consumerKafka.subscribe(Collections.singletonList(this.topic))

  Executors.newSingleThreadExecutor.execute(
    new Runnable {
      override def run(): Unit = {
        while (true) {
          val records: ConsumerRecords[String, GenericRecord] = consumerKafka.poll(Long.MaxValue)

          val recordIterator = records.iterator()

          while(recordIterator.hasNext){
            val record = recordIterator.next()
            println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset())
          }
        }
      }
  })

}
