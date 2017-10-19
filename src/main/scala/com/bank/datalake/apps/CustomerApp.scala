package com.bank.datalake.apps


import com.bank.datalake.jobs.CustomerJob
import org.apache.spark.sql.SparkSession

object CustomerApp {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder
      .master("local")
      .appName("AccountApp")
      .config("spark.cassandra.connection.host", "localhost")
      .config("spark.serializer","org.apache.spark.serializer.KryoSerializer")
      .getOrCreate()

    val source = getClass.getResource("/dataExample/Customer.csv").toString

    CustomerJob.start(sparkSession, source)
  }
}
