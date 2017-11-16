package com.bank.datalake.model

import org.apache.solr.client.solrj.beans.Field

case class ConfigScala(

                        @(Field)
                      id:String,

                        @(Field)
                      name:String,

                        @(Field)
                      typeEntity:String,

                        @(Field)
                      inputs:List[InputScala],

                        @(Field)
                      outputs:List[OutputScala]
                 )