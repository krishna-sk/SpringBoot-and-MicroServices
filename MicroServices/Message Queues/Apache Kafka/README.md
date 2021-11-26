### Execution Flow

a. Start Zookeeper

```properties
C:\kafka_2.12-2.8.0> .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

It runs on port : 2181

b. Start Kafka Server

```properties
C:\kafka_2.12-2.8.0> .\bin\windows\kafka-server-start.bat  .\config\server.properties
```

It runs on port : 9092

c. Create Topic

```properties
C:\kafka_2.12-2.8.0> .\bin\windows\kafka-topics.bat  --create --zookeeper localhost:2181 --partitions 1 --topic common-data
```

d. Run Producer Application and Consumer Application

e. open Postman\
GET http://localhost:8080/qoute/create/NITA/5100\
GET http://localhost:8080/qoute/all
