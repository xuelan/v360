package com.bnp.datahub.model

import scala.reflect.runtime.universe._

case class Customer(
                     ID_RP_CUSTOMER: String,
                     ID_TLM: String,
                     LB_CIV: String,
                     FIRST_NM: String,
                     LAST_NM: String,
                     USED_NM: String,
                     LB_DT_BIRTH: String,
                     LB_BIRTH_PLACE: String,
                     LB_EMAIL: String,
                     LB_PROFSN: String,
                     LB_NTNLTY: String,
                     ADRS_NUM: String,
                     LB_ADRS_1: String,
                     LB_ADRS_2: String,
                     CP: String,
                     LB_CITY: String,
                     LB_CNTRY: String,
                     LIST_TYP_CARD: List[Card],
                     LIST_TYP_ACC: List[Account],
                     LIST_TYP_CNTR: List[Contract]
                   )

object Customer {

  def applyCard(ID_RP_CUSTOMER: String, LIST_TYP_CARD: List[Card]): Customer = new Customer(ID_RP_CUSTOMER, null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, LIST_TYP_CARD, null, null)
  def applyAcc(ID_RP_CUSTOMER: String, LIST_TYP_ACC: List[Account]): Customer = new Customer(ID_RP_CUSTOMER, null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, null, LIST_TYP_ACC, null)
  def applyCntr(ID_RP_CUSTOMER: String, LIST_TYP_CNTR: List[Contract]): Customer = new Customer(ID_RP_CUSTOMER, null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null, null, null, LIST_TYP_CNTR)

}