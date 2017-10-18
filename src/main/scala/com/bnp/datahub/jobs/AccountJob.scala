package com.bnp.datahub.jobs

import com.bnp.datahub.model._
import com.datastax.spark.connector._
import org.apache.spark.sql._
import org.apache.spark.sql.functions.{collect_list, struct}

import scala.collection.JavaConverters._


object AccountJob {
  def start(sparkSession: SparkSession, filePath: String):Unit = {

    val sqlContext = sparkSession.sqlContext


    val dataSet = sqlContext.read
                              .format("com.databricks.spark.csv")
                              .option("header", "true")
                              .option("delimiter",";")
                              .load(filePath)
                              .groupBy("ID_RP_CUSTOMER")
                              .agg(collect_list(
                                struct("ID_RP_CUSTOMER", "ID_RP_CUSTOMER","ACC_NUM","LB_ACC_TYP","LB_CURRENCY","BALANCE")
                              ).as("Set"))

    val listCustomer:List[AccountCustomer] = dataSet.rdd.collect().toList.map(
      (row: Row) => {
          val listAccounts:List[Account] = row.getAs[Seq[Row]](1).toList.map(
            row2 => {
              new Account(row2.get(1).toString,row2.get(2).toString,row2.get(3).toString,row2.get(4).toString,row2.get(5).toString.toFloat)
            }
          )
          AccountCustomer(row.get(0).toString, listAccounts.asJava)
        }
    )


//    dataSet.show(20, false)
//    println(listCustomer mkString "\n")

    val sparkContext = sparkSession.sparkContext

    val collection = sparkContext.parallelize(listCustomer.toSeq)

    collection.saveToCassandra("v360", "tb_customer", SomeColumns(
      "id_rp_customer",
      "set_acc"
    ))

    sparkSession.stop()
  }
}
