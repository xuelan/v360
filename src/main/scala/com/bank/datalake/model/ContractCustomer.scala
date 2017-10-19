package com.bank.datalake.model

case class Customer(
                        id_rp_customer: String,
                        id_tlm: String,
                        lb_civ: String,
                        first_nm: String,
                        last_nm: String,
                        used_nm: String,
                        lb_dt_birth: String,
                        lb_birth_place: String,
                        lb_email: String,
                        lb_profsn: String,
                        lb_ntnlty: String,
                        adrs_num: String,
                        lb_adrs_1: String,
                        lb_adrs_2: String,
                        cp: String,
                        lb_city: String,
                        lb_cntry: String,
                        set_card: List[Card],
                        set_acc: List[Account],
                        set_cntr: List[Contract]
                   )

object Customer{

  def applyCustomer(id_rp_customer: String,
                    id_tlm: String,
                    lb_civ: String,
                    first_nm: String,
                    last_nm: String,
                    used_nm: String,
                    lb_dt_birth: String,
                    lb_birth_place: String,
                    lb_email: String,
                    lb_profsn: String,
                    lb_ntnlty: String,
                    adrs_num: String,
                    lb_adrs_1: String,
                    lb_adrs_2: String,
                    cp: String,
                    lb_city: String,
                    lb_cntry: String): Customer
  = new Customer(id_rp_customer,id_tlm,lb_civ,first_nm,last_nm, used_nm,lb_dt_birth,lb_birth_place,lb_email, lb_profsn,lb_ntnlty,adrs_num,lb_adrs_1, lb_adrs_2,cp,lb_city,lb_cntry, null, null, null)

  def applyContract(id_rp_customer: String, set_cntr: List[Contract]): Customer
          = new Customer(id_rp_customer,null,null,null,null,
                                        null,null,null,null,
                                        null,null,null,null,
                                        null,null,null,null,
                                        null, null, set_cntr)

  def applyCard(id_rp_customer: String, set_card: List[Card]): Customer
          = new Customer(id_rp_customer,null,null,null,null,
                                        null,null,null,null,
                                        null,null,null,null,
                                        null,null,null,null,
                                        set_card, null, null)

  def applyAccount(id_rp_customer: String, set_acc: List[Account]): Customer
          = new Customer(id_rp_customer,null,null,null,null,
                                        null,null,null,null,
                                        null,null,null,null,
                                        null,null,null,null,
                                        null, set_acc, null)
}