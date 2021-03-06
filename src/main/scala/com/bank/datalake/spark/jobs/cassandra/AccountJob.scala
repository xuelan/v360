package com.bank.datalake.spark.jobs.cassandra

import com.bank.datalake.model._
import com.datastax.spark.connector._
import org.apache.spark.sql._
import org.apache.spark.sql.functions.{collect_list, struct}

object AccountJob {
  def start(sparkSession: SparkSession, filePath: String):Unit = {

    val sqlContext = sparkSession.sqlContext


    val dataSet = sqlContext.read
                              .format("com.databricks.spark.csv")
                              .option("header", "true")
                              .option("delimiter",";")
                              .load(filePath)
                              .groupBy("id_rp_customer")
                              .agg(collect_list(
                                struct("id_rp_customer", "id_rp_customer","acc_num","lb_acc_typ","lb_currency","balance")
                              ).as("Set"))

    val listCustomer:List[Customer] = dataSet.rdd.collect().toList.map(
      (row: Row) => {
          val listAccounts:List[Account] = row.getAs[Seq[Row]](1).toList.map(
            row2 => {
              new Account(row2.get(1).toString,row2.get(2).toString,row2.get(3).toString,row2.get(4).toString,row2.get(5).toString.toFloat)
            }
          )
            Customer.applyAccount(row.get(0).toString, listAccounts)
        }
    )

    val sparkContext = sparkSession.sparkContext

    val collection = sparkContext.parallelize(listCustomer.toSeq)

    collection.saveToCassandra("v360", "tb_customer", SomeColumns(
      "id_rp_customer",
      "set_acc"
    ))

    sparkSession.stop()
  }
}
