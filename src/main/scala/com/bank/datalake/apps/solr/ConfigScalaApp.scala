package com.bank.datalake.apps.solr

import com.bank.datalake.jobs.solr.ConfigScalaJob
import org.apache.spark.sql.SparkSession

object ConfigScalaApp {
  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder
                                  .master("local")
                                  .appName("ConfigApp")
                                  .config("spark.serializer","org.apache.spark.serializer.KryoSerializer")
                                  .getOrCreate

    val source = getClass.getResource("/solr/dataExample/dataExample2.json").toString

    ConfigScalaJob.start(sparkSession, source)
  }
}