package com.bnp.datahub.model

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
                     lb_cntry: String
                   )
