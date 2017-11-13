// A Common 360° view of customer for retail banking

// Data cassandra.modeling based on DSE 5.1 (Cassandra + integreted search solr)
// Nosql approch:
// 1. tb_customer (with sub-collections of use defined type)
// 2. tb_transaction


////////////////////////////////////////  Keyspace ////////////////////////////////////////////

drop keyspace v360;

//Always 'NetworkTopologyStrategy', avoird 'SimpleStrategy'
create keyspace v360 WITH REPLICATION = { 'class' : 'NetworkTopologyStrategy', 'SearchGraphAnalytics' : 1 };


//////////////////////////////////////// UDT (USER DEFINED TYPE) ////////////////////////////////////////////

drop type v360.card;

create type v360.card (
  id_rp_customer text,
  card_num text,
  lb_card_typ text,
  lb_deb_typ text,
  dt_exp text,
  acc_num_attch text,
  card_state text
);

drop type v360.account;

create type v360.account (
  id_rp_customer text,
  acc_num text,
  lb_acc_typ text,
  lb_currency text,
  balance float
);

drop type v360.contract;

create type v360.contract (
  id_rp_customer text,
  cntr_num text,
  lb_cntr_typ text,
  dt_effct timestamp
);


////////////////////////////////////////  TABLE ////////////////////////////////////////////


drop table v360.tb_customer;

create table v360.tb_customer(
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
  set_card frozen<set<card>>,
  set_acc frozen<set<account>>,
  set_cntr frozen<set<contract>>,
  primary key (id_rp_customer)
);



//tb_transaction..... TODO with bucket(slice of month...)
// ((id_rp_customer, bucket), id_trnsc)  + index of rs_comm


/////////////////////////////////////////   INDEX   ///////////////////////////////////////////


DROP SEARCH INDEX ON v360.tb_customer;

create search index on v360.tb_customer
  with columns
    first_nm,
    last_nm,
    lb_email,
    lb_profsn,
    set_card,
    set_acc,
    set_cntr
;

//Normal fields
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer SET field[@name='first_nm']@type = 'TextField';
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer SET field[@name='last_nm']@type = 'TextField';
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer SET field[@name='lb_profsn']@type = 'TextField';
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer SET field[@name='lb_email']@type = 'StrField';

//Set_card
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer drop field[@name='set_card.id_rp_customer'];
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer drop field[@name='set_card.lb_card_typ'];
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer drop field[@name='set_card.lb_deb_typ'];
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer drop field[@name='set_card.dt_exp'];
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer drop field[@name='set_card.acc_num_attch'];
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer drop field[@name='set_card.card_state'];
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer set field[@name='set_card.card_num']@type = 'StrField';

//Set_acc
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer drop field[@name='set_acc.id_rp_customer'];
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer drop field[@name='set_acc.lb_acc_typ'];
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer drop field[@name='set_acc.lb_currency'];
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer drop field[@name='set_acc.balance'];
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer set field[@name='set_acc.acc_num']@type = 'StrField';

//Set_cntr
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer drop field[@name='set_cntr.id_rp_customer'];
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer drop field[@name='set_cntr.dt_effct'];
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer set field[@name='set_cntr.lb_cntr_typ']@type = 'TextField';
ALTER SEARCH INDEX SCHEMA ON v360.tb_customer set field[@name='set_cntr.cntr_num']@type = 'StrField';


RELOAD SEARCH INDEX ON v360.tb_customer;
REBUILD SEARCH INDEX ON v360.tb_customer;


/////////////////////////////////////////   INSERT   ///////////////////////////////////////////


insert into v360.tb_customer (
  id_rp_customer,
  id_tlm,
  lb_civ,
  first_nm,
  last_nm,
  used_nm,
  lb_dt_birth,
  lb_birth_place,
  lb_email,
  lb_profsn,
  lb_ntnlty,
  adrs_num,
  lb_adrs_1,
  lb_adrs_2,
  cp,
  lb_city,
  lb_cntry,
  set_card,
  set_acc,
  set_cntr
) values (
  '1',
  '0000008260',
  'Mr',
  'vinay',
  'HUET' ,
  '',
  '1978-03-15',
  'CARENTAN LES MARAIS',
  'vinay.HUET@gmail.com',
  'Assistant',
  'France',
  '2',
  'rue du pivert',
  '',
  '77360',
  'CARENTAN LES MARAIS',
  'France',
  {
    {id_rp_customer:'1',
    card_num:'4974000000019467',
    lb_card_typ:'MasterCard',
    lb_deb_typ:'Deferred Debit',
    dt_exp:'1997-04-20',
    acc_num_attch:'FR76 3000 4022 4400 0055 1234 556',
    card_state:'active'}
  },

  {
    {id_rp_customer: '1',
    acc_num: 'FR76 3000 4022 4400 0055 1234 556',
    lb_acc_typ: 'Account A',
    lb_currency: 'EUR',
    balance: 62919.4},

    {id_rp_customer: '1',
    acc_num: 'FR76 3000 4022 4400 0055 1234 557',
    lb_acc_typ: 'Account A',
    lb_currency: 'EUR',
    balance: 52919.4}
  },

  {
    {id_rp_customer: '1',
    cntr_num : '27594',
    lb_cntr_typ : 'ASSURANCE LIFE',
    dt_effct : '2007-09-04'}
  }
);


/////////////////////////////////////////   SELECT   ///////////////////////////////////////////


SELECT * from v360.tb_customer;

SELECT * from v360.tb_customer WHERE solr_query = '{"q":"first_nm:*na*"}';

SELECT * from v360.tb_customer WHERE solr_query = '+{!tuple}customer_card.acc_num:4974000000019467';

SELECT * from v360.tb_customer WHERE solr_query = '-{!tuple}set_cntr.lb_cntr_typ:*ASS*';



////////////////////////////////////// Impact from 1-1 to 1-N //////////////////////////////////////////////

// On UDT column your can't do : UDT => Set<UDT> with alter table
// Solution : 1. alter add column Set<UDT>
//            2. spark feeds the column Set<UDT>
//            3. alter drop the column UDT


