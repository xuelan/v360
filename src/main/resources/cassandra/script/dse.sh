/bin/dse cassandra                    #Transactional only
/bin/dse cassandra -g                 #DSE Graph
/bin/dse cassandra -k                 #DSE Analytics with Spark
/bin/dse cassandra -s                 #DSE Search

# Check status all dse level
/bin/dsetool status

# Check node status at only cassandra level
/bin/nodetool status

# Use sudo if needed
/bin/dse cassandra-stop

# Use sudo if needed
/bin/dse cassandra-stop -p PID

# Check index status
/bin/dsetool core_indexing_status v360.tb_customer --a