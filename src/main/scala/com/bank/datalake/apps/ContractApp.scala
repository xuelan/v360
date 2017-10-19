package com.bank.datalake.apps

import com.bank.datalake.jobs.ContractJob
import org.apache.spark.sql.SparkSession

object ContractApp {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder
                                  .master("local")
                                  .appName("ContractApp")
                                  .config("spark.cassandra.connection.host", "localhost")
                                  .config("spark.serializer","org.apache.spark.serializer.KryoSerializer")
                                  .getOrCreate()

    val source = getClass.getResource("/dataExample/Contract.csv").toString

    ContractJob.start(sparkSession, source)
  }
}
