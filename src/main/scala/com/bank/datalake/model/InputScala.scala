package com.bank.datalake.model

import org.apache.solr.client.solrj.beans.Field

import scala.annotation.meta.field

case class InputScala(
                       @(Field @field)
                      id:String,

                       @(Field @field)
                      inputType:String,

                       @(Field @field)
                      inputName:String,

                       @(Field @field)
                      address:String)