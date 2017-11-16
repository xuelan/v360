package com.bank.datalake.jobs.solr

import com.bank.datalake.model._
import org.apache.solr.client.solrj.impl.HttpSolrClient
import org.apache.spark.sql.SparkSession
import scala.collection.JavaConverters._

object ConfigScalaJob {

  def start(sparkSession: SparkSession, filePath: String):Unit = {

    import sparkSession.implicits._

    val configRdd = sparkSession.read
                                .option("multiLine", true)
                                .json(filePath)
                                .as[ConfigScala]
                                .rdd

    val configs:List[ConfigScala] = configRdd.collect.toList

    val urlString = "http://localhost:8983/solr/xsu_test/"
    val solr = new HttpSolrClient.Builder(urlString).build

    solr.addBeans(configs.asJava)
    solr.commit()

  }
}
