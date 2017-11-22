package com.bank.datalake.spark.apps.cassandra

import com.bank.datalake.spark.jobs.cassandra.CustomerJob
import org.apache.spark.sql.SparkSession

object CustomerApp {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder
                                  .master("local")
                                  .appName("CustomerApp")
                                  .config("spark.cassandra.connection.host", "localhost")
                                  .config("spark.serializer","org.apache.spark.serializer.KryoSerializer")
                                  .getOrCreate()

    val source = getClass.getResource("/cassandra/dataExample/Customer.csv").toString

    CustomerJob.start(sparkSession, source)
  }
}
