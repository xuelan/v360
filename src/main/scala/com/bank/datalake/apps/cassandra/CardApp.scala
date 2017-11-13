package com.bank.datalake.apps.cassandra

import com.bank.datalake.jobs.cassandra.CardJob
import org.apache.spark.sql.SparkSession

object CardApp {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder
                                  .master("local")
                                  .appName("CardApp")
                                  .config("spark.cassandra.connection.host", "localhost")
                                  .config("spark.serializer","org.apache.spark.serializer.KryoSerializer")
                                  .getOrCreate()

    val source = getClass.getResource("/cassandra/dataExample/Card.csv").toString

    CardJob.start(sparkSession, source)
  }
}
