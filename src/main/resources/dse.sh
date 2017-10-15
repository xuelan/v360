/bin/dse cassandra                    #Transactional only
/bin/dse cassandra -g                 #DSE Graph
/bin/dse cassandra -k                 #DSE Analytics with Spark
/bin/dse cassandra -s                 #DSE Search


/bin/nodetool status

/bin/dse cassandra-stop               # Use sudo if needed
ps auwx | grep dse
/bin/dse cassandra-stop -p PID        # Use sudo if needed
