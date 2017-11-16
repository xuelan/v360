package com.bank.datalake.model

case class ConfigScala(id:String, name:String, typeEntity:String, inputs:List[InputScala], outputs:List[OutputScala])