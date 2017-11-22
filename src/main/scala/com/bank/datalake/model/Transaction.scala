package com.bank.datalake.model

case class Transaction(
                       id_rp_customer: String,
                       id_trnsc: String,
                       timestamp_trnsc: String,
                       lb_trnsc_typ: String,
                       amount: Float
                      )