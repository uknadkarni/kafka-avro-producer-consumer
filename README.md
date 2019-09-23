# kafka-avro-producer-consumer
Kafka Producer and Consumer with Avro Schemas

Start the Confluent Platform
Instructions to download and install the Confluent Platform are available [here.] (https://docs.confluent.io/current/quickstart/ce-quickstart.html)
Start ElasticSearch

List topics to show that there are no topics created yet
	kafka-topics --list --zookeeper localhost:2181 | grep equities
Show that Schema Registry has no schemas
	localhost:8081/schemas/ids/1
Start Spring Boot Microservice
List topics to show that there is topic called equities
	kafka-topics --list --zookeeper localhost:2181 | grep equities
Show that Schema Registry has a schema with id=1
	localhost:8081/schemas/ids/1
POST a message to the Spring Boot App
localhost:8080/equities
Start kafka console consumer to show that there are messages in the topic “equties”
	./bin/kafka-avro-console-consumer --topic equities --bootstrap-server localhost:9092 --from-beginning
Show the Connector config file
	vi etc/kafka-connect-elasticsearch/quickstart-elasticsearch.properties
List Connector Plugins on the Worker
	curl localhost:8083/connector-plugins | jq
List Active Connectors
curl localhost:8083/connectors | jq
Load Elastic Search Sink Connector
	confluent local load elasticsearch-sink             
Show status of the Connector
	confluent local status elasticsearch-sink 
POST a request to Spring Boot Application
View the Result in 
	Kafka console consumer
	ElasticSearch
