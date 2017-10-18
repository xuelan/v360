package com.bnp.datahub.model

case class Account(
              id_rp_customer: String,
              acc_num: String,
              lb_acc_typ: String,
              lb_currency: String,
              balance: Float = 0
            )