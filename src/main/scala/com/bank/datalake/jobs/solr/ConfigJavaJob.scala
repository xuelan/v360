package com.bank.datalake.jobs.solr

import java.util

import com.bank.datalake.model._
import org.apache.solr.client.solrj.impl.HttpSolrClient
import org.apache.solr.common.SolrInputDocument
import org.apache.spark.sql.SparkSession


object ConfigJavaJob {

  def start(sparkSession: SparkSession, filePath: String):Unit = {

    import sparkSession.implicits._

    val configRdd = sparkSession.read
                                .option("multiLine", true)
                                .json(filePath)
                                .as[ConfigScala]
                                .rdd

    val configs:List[ConfigScala] = configRdd.collect.toList

    val docs = new util.ArrayList[SolrInputDocument]

    configs.map(
      config => {
        val doc = new SolrInputDocument()
        doc.addField("id", config.id)
        doc.addField("name", config.name)
        doc.addField("typeEntity", config.typeEntity)

        config.inputs.map(
          input => {
            val inputDoc = new SolrInputDocument()
            inputDoc.addField("id", input.id)
            inputDoc.addField("inputType", input.inputType)
            inputDoc.addField("inputName", input.inputName)
            inputDoc.addField("address", input.address)
            doc.addChildDocument(inputDoc)
        })

        config.outputs.map(
          output => {
            val outputDoc = new SolrInputDocument()
            outputDoc.addField("id", output.id)
            outputDoc.addField("outputType", output   .outputType)
            outputDoc.addField("outputName", output.outputName)
            outputDoc.addField("address", output.address)
            doc.addChildDocument(outputDoc)
          }
        )

        docs.add(doc)
      }
    )

    val urlString = "http://localhost:8983/solr/xsu_test/"
    val solr = new HttpSolrClient.Builder(urlString).build

    solr.add(docs)
    solr.commit()

  }
}
