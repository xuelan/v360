package com.bank.datalake.jobs.cassandra

import com.bank.datalake.model._
import com.datastax.spark.connector._
import org.apache.spark.sql._


object CustomerJob {
  def start(sparkSession: SparkSession, filePath: String):Unit = {

    val sqlContext = sparkSession.sqlContext


    val dataFrame = sqlContext.read
                              .format("com.databricks.spark.csv")
                              .option("header", "true")
                              .option("delimiter",";")
                              .load(filePath)

    dataFrame.show(20, false)

    val seq = dataFrame.rdd.map(
                    row => Customer.applyCustomer(
                                Option(row.get(0)).getOrElse("null").toString,
                                Option(row.get(1)).getOrElse("null").toString,
                                Option(row.get(2)).getOrElse("null").toString,
                                Option(row.get(3)).getOrElse("null").toString,
                                Option(row.get(4)).getOrElse("null").toString,
                                Option(row.get(5)).getOrElse("null").toString,
                                Option(row.get(6)).getOrElse("null").toString,
                                Option(row.get(7)).getOrElse("null").toString,
                                Option(row.get(8)).getOrElse("null").toString,
                                Option(row.get(9)).getOrElse("null").toString,
                                Option(row.get(10)).getOrElse("null").toString,
                                Option(row.get(11)).getOrElse("null").toString,
                                Option(row.get(12)).getOrElse("null").toString,
                                Option(row.get(13)).getOrElse("null").toString,
                                Option(row.get(14)).getOrElse("null").toString,
                                Option(row.get(15)).getOrElse("null").toString,
                                Option(row.get(16)).getOrElse("null").toString
                    )
    ).collect().toSeq

    val sparkContext = sparkSession.sparkContext

    val collection = sparkContext.parallelize(seq)

    collection.saveToCassandra("v360", "tb_customer", SomeColumns(
      "id_rp_customer",
      "id_tlm",
      "lb_civ",
      "first_nm",
      "last_nm",
      "used_nm",
      "lb_dt_birth",
      "lb_birth_place",
      "lb_email",
      "lb_profsn",
      "lb_ntnlty",
      "adrs_num",
      "lb_adrs_1",
      "lb_adrs_2",
      "cp",
      "lb_city",
      "lb_cntry"
    ))

    sparkSession.stop()
  }

}
