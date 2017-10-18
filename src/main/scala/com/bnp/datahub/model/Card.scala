package com.bnp.datahub.model

case class Card(
                id_rp_customer: String,
                card_num: String,
                lb_card_typ: String,
                lb_deb_typ: String,
                dt_exp: String,
                acc_num_attch: String,
                card_state: String
               )