package com.bank.datalake.apps.solr

import com.bank.datalake.jobs.solr.ConfigJob
import org.apache.spark.sql.SparkSession

object ConfigApp {
  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder
      .master("local")
      .appName("ConfigApp")
      .config("spark.cassandra.connection.host", "localhost")
      .config("spark.serializer","org.apache.spark.serializer.KryoSerializer")
      .getOrCreate

    val source = getClass.getResource("/solr/dataExample/dataExample.json").toString

    ConfigJob.start(sparkSession, source)
  }
}
