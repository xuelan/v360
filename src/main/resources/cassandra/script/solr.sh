#!/usr/bin/env bash

#create a new core
docker exec -it --user=solr my_solr bin/solr create_core -c xsu_test2

#query child doc
curl http://localhost:8983/solr/xsu_test/query -d 'q=(typeEntity:bank)&fl=id,name,typeEntity,[child parentFilter=typeEntity:bank]'