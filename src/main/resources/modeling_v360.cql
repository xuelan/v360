DROP TYPE TYP_CARD;

CREATE TYPE TYP_CARD (
  ID_RP_CUSTOMER text, 
  CARD_NUM text,
  LB_CARD_TYP text,
  LB_DEB_TYP text,
  DT_EXP date,
  ACC_NUM_ATTCH text,
  CARD_STATE text
); 

DROP TYPE TYP_ACC;
  
CREATE TYPE TYP_ACC (
  ID_RP_CUSTOMER text, 
  ACC_NUM text,
  LB_ACC_TYP text,
  LB_CURRENCY text,
  BALANCE float  
); 

DROP TYPE TYP_CNTR;

CREATE TYPE TYP_CNTR ( 
  ID_RP_CUSTOMER text,	
  CNTR_NUM text,
  LB_CNTR_TYP text,
  DT_EFFCT date
);


DROP TABLE TB_CUSTOMER; 

CREATE TABLE TB_CUSTOMER ( 
       ID_RP_CUSTOMER text PRIMARY KEY, 
       ID_TLM text, 
       LB_CIV text,
       FIRST_NM text,
       LAST_NM text,
       USED_NM text,
       LB_DT_BIRTH text,
       LB_BIRTH_PLACE text,
       LB_EMAIL text,
       LB_PROFSN text,
       LB_NTNLTY text,
       ADRS_NUM text,
       LB_ADRS_1 text,
       LB_ADRS_2 text,
       CP text,
       LB_CITY text,
	   LB_CNTRY text,
       LIST_TYP_CARD list<FROZEN <TYP_CARD>>, 
       LIST_TYP_ACC list<FROZEN <TYP_ACC>>,
       LIST_TYP_CNTR list<FROZEN <TYP_CNTR>>
); 
  
 

INSERT INTO TB_CUSTOMER (
    ID_RP_CUSTOMER,
    ID_TLM,
    LB_CIV,
    FIRST_NM,
    LAST_NM,
    USED_NM,
    LB_DT_BIRTH,
    LB_BIRTH_PLACE,
    LB_EMAIL,
    LB_PROFSN,
    LB_NTNLTY,
    ADRS_NUM,
    LB_ADRS_1,
    LB_ADRS_2,
    CP,
    LB_CITY,
    LB_CNTRY,
    LIST_TYP_CARD,
    LIST_TYP_ACC,
    LIST_TYP_CNTR
) VALUES (
  '1',
  '9615483774',
  'Mr',
  'PAUL',
  'Potier' ,
  '',
  '1990-07-01',
  'Paris',
  'paul.potier@gmail.com',
  'CONSULTANT IT',
  'FRANCE',
  '2',
  'rue Sophie Pasquier',
  '',
  '75001',
  'Paris',
  'FRANCE',
  [ {ID_RP_CUSTOMER: '1',
  	 CARD_NUM : '3000 6762 3079 7588',
     LB_CARD_TYP : 'CARTE VISA PREMIER',
     LB_DEB_TYP : 'Immediate Debit',
     DT_EXP : '2020-02-02',
     ACC_NUM_ATTCH : 'FR76 3000 4022 4400 0055 1234 556',
     CARD_STATE : 'active'},
    
    {ID_RP_CUSTOMER: '1',
	 CARD_NUM : '3000 6762 3079 7589',
     LB_CARD_TYP : 'MASTERCARD',
     LB_DEB_TYP : 'Immediate Debit',
     DT_EXP : '2020-01-02',
     ACC_NUM_ATTCH : 'FR76 3000 4022 4400 0055 1234 526',
     CARD_STATE : 'active'}
  ],
  
  [ {ID_RP_CUSTOMER: '1',
  	 ACC_NUM : 'FR76 3000 4022 4400 0055 1234 556',
     LB_ACC_TYP : 'Compte à terme',
     LB_CURRENCY : 'EUR',
     BALANCE : 62919.4}
  ],
  
  [ {ID_RP_CUSTOMER: '1',
  	 CNTR_NUM : '705116237051162318187051162318',
     LB_CNTR_TYP : 'Assurance Vie',
     DT_EFFCT : '2016-08-01'}
  ]
);


// Update

INSERT INTO TB_CUSTOMER (
    ID_RP_CUSTOMER,
    LIST_TYP_CARD
) VALUES (
  '1',
  [ {ID_RP_CUSTOMER: '1',
  	 CARD_NUM : '3000 6762 3079 7588',
     LB_CARD_TYP : 'CARTE VISA PREMIER',
     LB_DEB_TYP : 'Immediate Debit',
     DT_EXP : '2020-02-02',
     ACC_NUM_ATTCH : 'FR76 3000 4022 4400 0055 1234 556',
     CARD_STATE : 'active'},

    {ID_RP_CUSTOMER: '1',
     CARD_NUM : '3000 6762 3079 7589',
     LB_CARD_TYP : 'MASTERCARD',
     LB_DEB_TYP : 'Immediate Debit',
     DT_EXP : '2020-01-02',
     ACC_NUM_ATTCH : 'FR76 3000 4022 4400 0055 1234 526',
     CARD_STATE : 'active'},

    {ID_RP_CUSTOMER: '1',
     CARD_NUM : '3000 6762 3079 7588',
     LB_CARD_TYP : 'CARTE VISA',
     LB_DEB_TYP : 'Immediate Debit',
     DT_EXP : '2019-02-02',
     ACC_NUM_ATTCH : 'FR76 3000 4022 4400 0055 1234 556',
     CARD_STATE : 'active'}
  ]
);

select * from TB_CUSTOMER; 
