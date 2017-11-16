package com.bank.datalake.model

import org.apache.solr.client.solrj.beans.Field

case class InputScala(
                       @Field
                      id:String,

                       @Field
                      inputType:String,

                       @Field
                      inputName:String,

                       @Field
                      address:String)