#!/usr/bin/env bash

# Run zk
docker run --name some-zookeeper --restart always -d zookeeper

# Run zk Client
docker run -it --rm --link some-zookeeper:zookeeper zookeeper zkCli.sh -server zookeeper




###################### Solr 7.1 ######################

# Pull image
docker pull solr

# Run solr
docker run --name my_solr -d -p 8983:8983 -t solr

# Create a new solr collection
docker exec -it --user=solr my_solr bin/solr create_core -c gettingstarted



###################### Elasticsearch 6.0.0 ######################

# Pull image
docker pull docker.elastic.co/elasticsearch/elasticsearch:6.0.0

# Run Elasticsearch
docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:6.0.0



###################### Confluent 3.0.1 Including Kafka 0.10.0.1 release ######################

# Pull image
docker pull confluent/kafka

# Start Zookeeper and expose port 2181 for use by the host machine
docker run -d --name zookeeper -p 2181:2181 confluent/zookeeper

# Start Kafka and expose port 9092 for use by the host machine
docker run -d --name kafka -p 9092:9092 --link zookeeper:zookeeper confluent/kafka

# Start Schema Registry and expose port 8081 for use by the host machine
docker run -d --name schema-registry -p 8081:8081 --link zookeeper:zookeeper \
    --link kafka:kafka confluent/schema-registry

# Start REST Proxy and expose port 8082 for use by the host machine
docker run -d --name rest-proxy -p 8082:8082 --link zookeeper:zookeeper \
    --link kafka:kafka --link schema-registry:schema-registry confluent/rest-proxy
If you're using boot2docker, you'll need to adjust how you run Kafka:

# Get the IP address of the docker machine
DOCKER_MACHINE=`boot2docker ip`

# Start Kafka and expose port 9092 for use by the host machine
# Also configure the broker to use the docker machine's IP address
docker run -d --name kafka -p 9092:9092 --link zookeeper:zookeeper \
    --env KAFKA_ADVERTISED_HOST_NAME=$DOCKER_MACHINE confluent/kafka