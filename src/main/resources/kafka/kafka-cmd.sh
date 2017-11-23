#!/usr/bin/env bash

# confluent : Rest proxy
curl -X POST -H "Content-Type: application/vnd.kafka.json.v1+json" --data '{"records":[{"value":{"id_rp_customer":"1", "id_trnsc":"2", "timestamp_trnsc":"1111111", "lb_trnsc_typ": "333", "amount":"2342"}}]}' "http://0.0.0.0:8082/topics/transaction"


# kafka : start zk
bin/zookeeper-server-start.sh -daemon config/zookeeper.properties

# kafka : start kafka
bin/kafka-server-start.sh -daemon config/server.properties

# kafka : create a topic
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic transaction

# kafka : produce a message
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic transaction

# kafka : consume a message
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic transaction --from-beginning