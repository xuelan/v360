package com.bank.datalake.util

import java.io.ByteArrayOutputStream
import java.util.concurrent.atomic.AtomicReference

import com.bank.datalake.model.Transaction
import com.sksamuel.avro4s.RecordFormat
import org.apache.avro.Schema
import org.apache.avro.Schema.Parser
import org.apache.avro.generic.{GenericDatumWriter, GenericRecord}
import org.apache.avro.io.{BinaryDecoder, DatumReader, DecoderFactory, EncoderFactory}
import org.apache.avro.specific.SpecificDatumReader

import scala.io.Source
import scala.util.Try

object AvroUtils {
  def getGenericRecord(transaction: Transaction): GenericRecord ={
    RecordFormat[Transaction].to(transaction)
  }

  def getUserObject[A](message: Array[Byte], mapper: DomainMapper[A]): Try[A] = {
    Try({
      // Deserialize and create generic record
      val reader: DatumReader[GenericRecord] = new SpecificDatumReader[GenericRecord](mapper.getSchema())
      val decoder: BinaryDecoder = DecoderFactory.get().binaryDecoder(message, null)
      // Make user object
      mapper.getUserObject(reader.read(null, decoder))
    })
  }

  def toBytes(record: GenericRecord, schema: Schema): Array[Byte] = {
    val writer = new GenericDatumWriter[GenericRecord](schema)
    val out = new ByteArrayOutputStream()
    val encoder = EncoderFactory.get().binaryEncoder(out, null)
    writer.write(record, encoder)
    encoder.flush() // !
    out.toByteArray
  }

}

abstract class DomainMapper[A](var schemaName:String) extends Serializable {
  private val schemaString = Source.fromURL(getClass.getResource(schemaName)).mkString
  private val schema = (new AtomicReference[Option[Schema]](None))

  def getSchema(): Schema = {
    schema.compareAndSet(None, Option(new Parser().parse(schemaString)))
    schema.get().get
  }

  def getMapData(data: A): GenericRecord
  def getUserObject(record: GenericRecord) : A
}


