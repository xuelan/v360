
package com.bank.datalake.util


import com.bank.datalake.model.Transaction
import com.sksamuel.avro4s.RecordFormat
import org.apache.avro.generic.GenericRecord

object AvroUtils {
  def getGenericRecord(transaction: Transaction): GenericRecord ={
    RecordFormat[Transaction].to(transaction)
  }
}
