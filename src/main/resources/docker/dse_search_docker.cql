drop keyspace test_keyspace;

create keyspace v360 WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

drop table test_keyspace.test_table;

create table test_keyspace.test_table (
id text,
value text,
primary key (id)
);


DROP SEARCH INDEX ON test_keyspace.test_table;


create search index on test_keyspace.test_table
with columns
id
;

insert into test_keyspace.test_table
(id, value) values ('1','test')
;

insert into test_keyspace.test_table
(id, value) values ('2','test-2')
;


SELECT * from test_keyspace.test_table WHERE solr_query = '{"q":"id:*2*"}';


