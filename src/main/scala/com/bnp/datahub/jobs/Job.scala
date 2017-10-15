package com.bnp.datahub.jobs

import com.bnp.datahub.model._
import org.apache.spark.sql._
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema
import org.apache.spark.sql.functions.collect_list
import org.apache.spark.sql.functions.struct
import com.datastax.spark.connector._

import scala.collection.mutable
import scala.reflect.runtime.universe._


object Job {
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

    val listCustomer:List[Customer] = dataSet.rdd.collect().toList.map(
      (row: Row) => {
          val listAccounts:List[Account] = row.getAs[Seq[Row]](1).toList.map(
            row2 => {
              new Account(row2.get(1).toString,row2.get(2).toString,row2.get(3).toString,row2.get(4).toString,row2.get(5).toString.toFloat)
            }
          )
          Customer.applyAcc(row.get(0).toString, listAccounts)
        }
    )


    dataSet.show(20, false)

    println(listCustomer mkString "\n")

    val sparkContext = sparkSession.sparkContext

    val collection = sparkContext.parallelize(listCustomer)

    collection.saveToCassandra("v360", "TB_CUSTOMER", SomeColumns(
      "ID_RP_CUSTOMER",
      "ID_TLM",
      "LB_CIV",
      "FIRST_NM",
      "LAST_NM",
      "USED_NM",
      "LB_DT_BIRTH",
      "LB_BIRTH_PLACE",
      "LB_EMAIL",
      "LB_PROFSN",
      "LB_NTNLTY",
      "ADRS_NUM",
      "LB_ADRS_1",
      "LB_ADRS_2",
      "CP",
      "LB_CITY",
      "LB_CNTRY",
      "LIST_TYP_CARD",
      "LIST_TYP_ACC",
      "LIST_TYP_CNTR"
    ))
    sparkSession.stop()
  }
}
