package com.bank.datalake.model

import org.apache.solr.client.solrj.beans.Field

import scala.annotation.meta.field

case class OutputScala(
                        @(Field @field)
                        id:String,

                        @(Field @field)
                        outputType:String,

                        @(Field @field)
                        outputName:String,

                        @(Field @field)
                        address:String
                      )