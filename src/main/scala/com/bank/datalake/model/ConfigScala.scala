package com.bank.datalake.model

import org.apache.solr.client.solrj.beans.Field

import scala.annotation.meta.field

case class ConfigScala(

                        @(Field @field)
                      id:String,

                        @(Field @field)
                      name:String,

                        @(Field @field)
                      typeEntity:String,

                        @(Field @field)
                      inputs:List[InputScala],

                        @(Field @field)
                      outputs:List[OutputScala]
                 )