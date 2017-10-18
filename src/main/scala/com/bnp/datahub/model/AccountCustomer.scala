package com.bnp.datahub.model

import java.util.List

case class AccountCustomer(
                     id_rp_customer: String,
                     set_acc: List[Account]
                    )