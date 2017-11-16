package com.bank.datalake.jobs.solr

import java.util

import com.bank.datalake.model._
import org.apache.solr.client.solrj.impl.HttpSolrClient
import org.apache.solr.common.SolrInputDocument
import org.apache.spark.sql.SparkSession


object ConfigJob {

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

        val inputsList = new util.ArrayList[SolrInputDocument]
        //val docInputs = new SolrInputDocument()

        config.inputs.map(
          input => {

            val inputDoc = new SolrInputDocument()

            inputDoc.addField("id", input.id)
            inputDoc.addField("inputType", input.inputType)
            inputDoc.addField("inputName", input.inputName)
            inputDoc.addField("address", input.address)

            inputsList.add(inputDoc)
            //docInputs.addChildDocument(inputDoc)
        })

        //doc.addChildDocuments(inputsList)
       // doc.addField("inputs", docInputs)


        val outputsList = new util.ArrayList[SolrInputDocument]

       // val docOutputs = new SolrInputDocument()

        config.outputs.map(
          output => {
            val outputDoc = new SolrInputDocument()

            outputDoc.addField("id", output.id)
            outputDoc.addField("outputType", output.outputType)
            outputDoc.addField("outputName", output.outputName)
            outputDoc.addField("address", output.address)

            //doc.addChildDocument(outputDoc)
            outputsList.add(outputDoc)
          }
        )


        doc.addField("id", config.id)
        doc.addField("name", config.name)
        doc.addField("typeEntity", config.typeEntity)

//        val childrensInputs = new SolrInputDocument()
//        childrensInputs.addChildDocuments(inputsList)
        doc.addField("inputs", inputsList)

//        val childrensOutputs = new SolrInputDocument()
//        childrensOutputs.addChildDocuments(outputsList)
        doc.addField("outputs", outputsList)


        docs.add(doc)
      }
    )

    val urlString = "http://localhost:8983/solr/xsu_test/"
    val solr = new HttpSolrClient.Builder(urlString).build

    solr.add(docs)
    solr.commit()

  }
}
