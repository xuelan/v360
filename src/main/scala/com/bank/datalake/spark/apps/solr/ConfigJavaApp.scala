package com.bank.datalake.spark.apps.solr

import com.bank.datalake.spark.jobs.solr.ConfigJavaJob
import org.apache.spark.sql.SparkSession

object ConfigJavaApp {
  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder
                                  .master("local")
                                  .appName("ConfigApp")
                                  .config("spark.serializer","org.apache.spark.serializer.KryoSerializer")
                                  .getOrCreate

    val source = getClass.getResource("/solr/dataExample/dataExample2.json").toString

    ConfigJavaJob.start(sparkSession, source)
  }
}
