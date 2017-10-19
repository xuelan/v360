create keyspace v360_multipe WITH REPLICATION = { 'class' : 'NetworkTopologyStrategy', 'SearchGraphAnalytics' : 1 };

drop table v360_multipe.tb_contract;

create table v360_multipe.tb_contract (
  id_rp_customer text,
  cntr_num text,
  lb_cntr_typ text,
  dt_effct timestamp,
  primary key ((id_rp_customer),cntr_num)
);

drop table v360_multipe.tb_account;

create table v360.tb_account (
  id_rp_customer text,
  acc_num text,
  lb_acc_typ text,
  lb_currency text,
  balance float,
  primary key ((id_rp_customer),acc_num)
);


drop table v360_multipe.tb_card;

create table v360_multipe.tb_card (
  id_rp_customer text,
  card_num text,
  lb_card_typ text,
  lb_deb_typ text,
  dt_exp text,
  acc_num_attch text,
  card_state text,
  primary key ((id_rp_customer),card_num)
);

drop table v360_multipe.tb_customer;

create table v360_multipe.tb_customer (
  id_rp_customer text,
  id_tlm text,
  lb_civ text,
  first_nm text,
  last_nm text,
  used_nm text,
  lb_dt_birth text,
  lb_birth_place text,
  lb_email text,
  lb_profsn text,
  lb_ntnlty text,
  adrs_num text,
  lb_adrs_1 text,
  lb_adrs_2 text,
  cp text,
  lb_city text,
  lb_cntry text,
  primary key (id_rp_customer)
);

