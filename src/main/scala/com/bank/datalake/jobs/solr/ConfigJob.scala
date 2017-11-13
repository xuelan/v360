package com.bank.datalake.jobs.solr

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{collect_list, struct}

object ConfigJob {
  def start(sparkSession: SparkSession, filePath: String):Unit = {

    val sqlContext = sparkSession.sqlContext

    val dataSet = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("delimiter",";")
      .load(filePath)
      .groupBy("id_rp_customer")
      .agg(collect_list(
        struct("id_rp_customer", "id_rp_customer", "card_num", "lb_card_typ", "lb_deb_typ", "dt_exp", "acc_num_attch", "card_state")
      ).as("Set"))
  }
}
