package com.bnp.datahub.model

case class Account(
              ID_RP_CUSTOMER: String,
              ACC_NUM: String,
              LB_ACC_TYP: String,
              LB_CURRENCY: String,
              BALANCE: Float = 0
            )


/*object Account{
  def apply(
             ID_RP_CUSTOMER: String,
             ACC_NUM: String,
             LB_ACC_TYP: String,
             LB_CURRENCY: String,
             BALANCE: Float = 0
           ): Account = new Account(ID_RP_CUSTOMER, ACC_NUM, LB_ACC_TYP, LB_CURRENCY, BALANCE)
}*/
