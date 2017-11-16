package com.bank.datalake.model

import org.apache.solr.client.solrj.beans.Field

case class OutputScala(
                        @(Field)
                        id:String,

                        @(Field)
                         outputType:String,

                        @(Field)
                        outputName:String,

                        @(Field)
                        address:String)