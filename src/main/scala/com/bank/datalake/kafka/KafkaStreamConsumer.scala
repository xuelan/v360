package com.bank.datalake.kafka

import java.util.Properties

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.kstream.{KStream, KStreamBuilder}
import org.apache.kafka.streams.processor.WallclockTimestampExtractor
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}


object KafkaStreamConsumer extends App {

  private val broker = "localhost:9092"
//  private val SCHEMA_URL = "http://localhost:8081"
  private val topic = "transaction"

  val props = new Properties()
  props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, broker)
  props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-predictions")
  props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, broker)
  props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName)
  props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.ByteArray().getClass().getName)
  props.put(StreamsConfig.TIMESTAMP_EXTRACTOR_CLASS_CONFIG, classOf[WallclockTimestampExtractor].getName)

  // setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
  //props.put(ConsumerConfig.AutoOffsetReset, "earliest")

  val builder: KStreamBuilder = new KStreamBuilder

  val source: KStream[String, Array[Byte]] = builder.stream(topic)

  //source.mapValues((record: Array[Byte]) => AvroUtils.toBytes(record))

  val streams: KafkaStreams = new KafkaStreams(builder, props)

}
