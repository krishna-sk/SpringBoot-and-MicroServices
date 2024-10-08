# Microservices

#### Table of Contents

- [Monolithic Application](#monolithic-application)
- [Microservice](#microservice)
- [Spring Cloud](#spring-cloud)
- [Register and Discovery Server](#register-and-discovery-server)
- [Microservice - Intra Communication](#microservice---intra-communication)
- [Load Balancer Client](#loadbalancerclient)
- [Spring Cloud - Config Server](#spring-cloud---config-server)
- [Distributed Tracing](#distributed-tracing)
- [Actuator and Admin Server](#actuator-and-admin-server)
- [Actuator - Admin Server/Client](#actuator---admin-serverclient)
- [Message Queues](#message-queues)
- [Spring Cloud API Gateway](#spring-cloud-api-gateway)

###### 03-November-2021

### Monolithic Application

- Project is a collection of Modules. [ Application is a collection of Service]\
  ex: Amazon Application\
   Services: Search, Cart, Payment, Feedback, Trace...etc

  Gmail Application\
   Services: User (Register, Login), Inbox, Sent, Drafts..etc

  Facebook Application\
  Services: Register, Login, Post, Comment, Like, Friends , Chat ..etc

**Monolithic Application** : Creating a single deployable component (single .jar or .war) for a Project is called as "Monolithic Application"

- Finally above projects converted into Amazon.war, Gmail.war ..etc\
  compile : .java ---> .class\
  Build : .class (+HTML,CSS,Images/..etc) - .jar / .war\
  jar - java archive (stand alone application type)\
  war - web archive (web application types)

- deploy : Place build file inside server and start server.

- Service Instance / Instance :- A successful Deployment setup/Environment

- Load : Client Request (internally one Request = one threads)

- Max Load : Max no.of request allowed by server (for parallel processing)

  - Default is : 200
  - server.jetty.threads.max = 200
  - server.tomcat.threads.max = 200

- Load, MaxLoad, LoadFactor
  - Load = Current no.of Request
  - Max Load = max no.of Request allowed
  - LoadFactor = Current Load/ Max Load

```textile
     LoadFactor = Current Load/ Max Load
                = 100 / 200
                = 0.5
     0 <= LoadFactor <= 1
```

- 0 <= LoadFactor <= 1

- Scaling:- it is a process of increase the performance of system/application.

**Horizontal Scaling :** Creating multiple Service Instances and handle using LBS(Load Balancer Server) is called as Horizontal Scaling.

**Vertical Scaling :** Enhance System hardware capacity. ie RAM, CPU ,HardDisk/SSD..etc

**Load Balancer Server** :- It will dispatch request to Actual Service instance.

- It will choose an Instance which has Less Load Factor.
- If all are having same LF then choose any one Instance in Random.

- LBS will not process request.
- It will dispatch request to ServiceInstance
- Every ServiceInstance is identified using InstanceId(HexaDecimal Value)

###### 06-November-2021

### Microservice

**Microservices :** An independent deployable component, that supports communication with other services. one service as one application. \[ 1 module = 1 project \]. A de-coupled architecture is called as Microservices.

### Monolithic vs Microservice

![](images/monolithic-vs-microservice.png)

### Microservice Architecture

![](images/microservice-architecture.png)

### Spring Cloud

###### 08-November-2021

Code/API/Tools to implement application.

**Difference between Cloud Computing and Spring Cloud:**

**Cloud Computing :** PaaS, SaaS, IaaS\
 Using Services for rental to deploy/run/moniter our application in internet. (Concept of POST development)\
ex: AWS, Google Cloud, MS-AZURE

**Spring Cloud :** open source Java API given by Pivotal team used to develop application using Microservices Design.

| Concepts                        | Tools/ Softwares                |
| ------------------------------- | ------------------------------- |
| Service(Microservice)           | Spring REST                     |
| Register & Discovery            | Eureka Server                   |
| Config Server                   | Cloud Config(GIT)               |
| API Gateway                     | Zuul(old)/Cloud Gateway         |
| LoadBalancer Consumer           | (old)Ribbon/Cloud Load Balancer |
| Message Queues                  | Apache Kafka                    |
| Distributed Tracing             | Zipkin and Sleuth               |
| Fault Tolerance Circuit Breaker | Hystrix(old)/Resillence4J       |
| Log Tracking                    | ELK                             |
| Security                        | JWT/OAuth 2.x                   |

#### 1. Register and Discovery Server:-

- Every Microservice must be registered with R&D Server.
  ie Provide ApplicationName(serviceId), InstanceId, HOST(IP), PORT, LoadFactor ..etc
- Spring Cloud Netflix Eureka Server.

- Eureka Server (Register & Discovery Server)
  | SERVICE-ID | INSTANCE-ID | HOST | PORT | LOADFACTOR |
  | ---------- | ----------- | ----------- | ---- | ---------- |
  | CART | CART-520 | 192.168.0.1 | 9690 | 0/200 |
  | CART | CART-850 | 192.168.0.2 | 9696 | 0/200 |
  | CART | CART-529 | 192.168.0.1 | 8080 | 0/200 |

- we are giving our Microservice details to eureka server.

- it is also called as publish operation.

#### 2. Config Server:-

- There can be multiple Microservice in our project.
- Every Microservice contains properties file (k=v)
- There can be some common key=val exist in every Microservice
- Such common key=val define outside of all Microservice and shared to all Microservice ie config server (github)

#### 3. API Gateway:-

- We should not provide multiple IP/PORT to Client Applications.
- All Microservices runs in private Network. Dont share with outside network
- There must be single entry and exit to Microservice Design/Apps.ie API Gateway
- Even API Gatway supports :

  - LoadBalancer (Choose an instance having less load)
  - Filters (Validate Request/Response)
  - Security (User and Role Management)

- API Gateway IP/PORT is given to client teams.
  Request execution flow\
  Client --> Request --> API Gateway --> Eureka --> Service(Microservice)--> Execute Service --> Response --> API Gateway --> Client
- Old Gateway name is : Netflix Zuul, Now we have new API Gtway : Cloud Gateway

#### 4. LoadBalancer Consumer / Microservice Intra communication (Consumer concepts):-

- One Microservice may need to communicate with other Microservice sometimes
- In that case we need Consumer concept(3).

  - DiscoveryClient
  - LoladBalancerClient (old Ribbon)
  - FeignClient \*\*\*\*

    Microservice(A) <---------\[Consumer\]-----------> Microservice(B)

#### 5. Message Queues:-

- To send data from Source Machine to Destination in continioues flow (with some time period) is called as Message Queue.

  Examples : Live Train status, Swiggy Delivery Status, Ola Cab status, Cricket Score..etc

- DataBase is used to store data
- Message Queues are used to trasfer data.
- Tools : ActiveMQ, RabbitMQ, Apache Kafka..etc

#### 6. Distributed Tracing :-

- There can be multiple Microservice in application.
- For a single request multiple Microservice can be executed (using Intra communication)
- I want to know ??? (ie Distributed Tracing)
  - How many Microservice executed for request?
  - What are they and execution order?
  - Time taken for Specific Microservice execution?
- Distributed Tracing -> Zipkin and Sleuth

#### 7. Fault Tolerance / Circuit Breaker:-

- In case Microservice executions there can be multiple issues thrown (Casecade exceptions)
- To avoid those for a period of time, execute some dummy services and give messages to endusers.\
  Ex: Try after some time. Service not exist!
- Stop execution of Actual service and give dummy output for some time.

#### 8. Log Tracking | Log Aggregation:-

- There can be multiple Microservice in application
- Every Microservice can have Log file
- Read all Log files data and show at one place, that is Log Aggregation
- ELK/Splunk ..etc are used for "Log Aggregation".

#### 9. Security :-

- Microservice is a stateless design (no HttpSession used at backend)
- So State-management is done at Client side\
  Angular -- ngrx\
  ReactJS -- redux
- **State Management** : Store and trace user data for a period of time(login to logout)
- **Statement Management** Can be done at backend side or frontend side.\
  HttpSession + Cookies are used in java for Statement Management
- Client side State Management, reduces burden on Server, network calls, easy to store and process.
- This security concept is implemented using JWT/SSO[OAuth 2.x] (JSON Web Token / Single Sign On {Oauth 2.x})

#### 10. Actuator with Admin Server:-

- Few readymade services required for any type of project like

  - App Started properly or not?
  - Beans created or not?
  - Environment details.
  - Mappings
  - Threads/Heap details
  - Cache details
  - Scheduler..etc
  - (generic services specially used for Production)

- **Actuator :** Actuator gives Production Ready Endpoints (Readymade services for Production Server). But , Actuator must be added in every Microservice. So, we need a common palce to view/access all services\
  ie : Admin Server (Central server)

**NOTE :**

- One Maven project can have how many parent projects?
- To link with multiple parents use BOM (Bill Of Materials)
  tags: "\<dependencyManagemenet>"
- Spring Cloud is a collection of Microservice Components as a pre-defined project, for every paroject this one must be added.
- But already Spring Boot is a Parent Project. So, this is added to our project using BOM (Bill Of Materials) using a tag "\<dependencyManagement>"
- If we are developing Spring Cloud Applications then internally it uses Spring boot concepts also.

###### 09-November-2021

### [Register and Discovery Server](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/MicroServices/Eureka%20Register%20%26%20Discovery%20Server)

**Register and Discovery Server :-[Netflix Eureka Server]**

- This server contains Microservice Details (knonw as ServiceInstance)\
   Details/ServiceInstance means\
   a. serviceId (application name)\
   b. InstanceId (Instance Number)\
   c. HOST/IP\
   d. PORT\
   e. Load Factor
- Eureka is a Register, It will never makes any HTTP call to Microservice
- Every Microservice Must be registered with Eureka, then only they can be called/executed/accessed by other Microservice or API Gateway.
- At Microservice application we need to add "eureka.client.register-with-eureka=true". But, Spring cloud parent has given same.
- Even for Eureka Server , Spring cloud is parent and given " eureka.client.register-with-eureka=true ". It must be set to FALSE, else dummy/UNKNOW object
  is creted for Eureka itself, which has no use(waste of memory) so we add "eureka.client.register-with-eureka=false" in properties file

- If one Microservice wants to communicate with another Microservice,we need Register (a place where Microservice details is stored) From Eureka, that can be fetched by adding "eureka.client.fetch-registry=true".Default value is true given by Spring cloud. So, write one time false in Eureka server.

**Note:**

- Eureka is a Register
- Eureka Stores Microservice details/serviceInstance
- Eureka can share one Microservice details with another Microservice
- Eureka never makes HTTP call to any App/Microservice

- PUBLISH : Register Microservice with eureka done using
- Add Eureka Discovey Client in POM.xml
- @EnableEurekaClient at Microservice main class
- Eureka location at Microservice properties

```text
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

- Register with Eureka as true

```text
   eureka.client.register-with-eureka=true
```

###### 10-November-2021

### [Microservice - Intra Communication](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/MicroServices/Microservice%20Intra%20Communication)

**Microservice --- Intra Communication**

- One Microservice can communicate with another Microservice to exchange data(or to execute operations)that is called as Intra-Communication.
- Creating a Communication link between multiple Microservice to exchange Data is called as "Intra Communication"

- Must use one Consumer for Microservice Intra-Communication

  - DiscoveryClient (legacy)
  - LoadBalancerClient \*\*
  - FeignClient **\***

- To make communication we need URL of Microservice
- Microservice **URI** can be fetch be from Eureka and create **URL**
- Then use RestTemplate and make CALL.

- DiscoveryClient is used to fetch Microservice Details as List<ServiceInstance> by taking serviceId as input.
- From ServiceInstance read URI at runtime(IP:PORT)
- add Path to URI that gives URL
- Use RestTemplate to make HTTP call and get Response.
- This DicoveryClient and RestTemplate code write inside one Consumer class (ie CartRestConsumer)

- We are using Eureka Server (Register and Discovery Server) to store our Microservice Data (Publish),to fetch other Microservice data(Find) using DiscoveryClient(I).
  - (ServiceInstance = ServiceId + InstanceId + HOST + PORT + LF)

###### 09-November-2021

#### CODING STEPS USING DISCOVERY CLIENT

- Autowire DiscoveryClient(I)

- call method getInstances(serviceId) that returns List<ServiceInstance>

- Read ServiceInstance at index#0 \
  ServiceInstance si = list.get(0);

- Read URI from SI\
  URI uri = si.getUri();

- Add Path to URI to get URL\
  String url = uri + "/path/path";

- Use RestTemplate and make HTTP call

#### Execution Flow:

1. Request comes from client/API Gateway...
2. Employee Service Executes EmployeeRestController
3. It is calling DepartmentConsumer method
4. DiscoveryClient code is executed (find[sid]) ,client.getInstances("deptServiceId");
5. It will goto eureka to find ServiceInstances of Department
6. Eureka Returns List<ServiceInstances> (as of now one ServiceInstance)
7. Read data from Index#0 , then Read URI add Path
8. take URL and use RestTemplate to call consumer
9. Request is made to Department Application
10. Dept Rest Controller is executed ang gives some output
11. Response is return back to Consumer class
12. RestTemplate Reading Response Into ResponseEntity<T> and return body to EmployeeRestController
13. Employee Rest Controller creates final response
14. Returns same back to Client/API Gateway...

- DiscoveryClient returns all ServiceInstances from Eurekaas List<SI>, we should choose any one instance manually.(It is not a better approch)

###### 10,11th-November-2021

### LoadBalancerClient

**LoadBalancerClient / LBC (Interface)**

- We use this time "LoadBalancerClient" that gets always one instance(ServiceInstace) which has less load factor.

- If we are running our application (Microservice) multiple times to create multiple ServiceInstances then must provide InstanceId, using
  **eureka.instance.instance-id=${spring.application.name}:${random.value}**
- InstanceId is not required in case of single instance.
- ${random.value} -- generates one hexa decimal value using a class "RandomValuePropertySource"

- We must add Cloud LoadBalancer Dependency to use "LoadBalancerClient"
- This client is used to fetch one ServiceInstance per request, from eureka server.
- Input is 'ServiceId'(spring.application.name) and Output is ServiceInstance.
- InstanceId need to provided to create multiple instances.\
   eureka.instance.instance-id=\***\*\_\*\***(String)
- It is going to choose an instance that has less load factor\
  [ min(List<LF>) --> InstanceId ---> ServiceInstance ]

- If all/multiple instances are having same LoadFactor,\
  (ex: IID#110 -> LF 0.3, IID#234 -> LF 0.3 , IID#875 -> LF 0.3), Choose any one Instance in Random [ ex: new Random().nextInt(3) ]
- LoadBalancerClient(I) and Implementation class is : BlockingLoadBalancerClient(C) ( old one : RibbonLoadBalancerClient(C) )
  Ribbon was netflix component (3rd party) now moved to Cloud LoadBalancer spring cloud component.
- ServiceInstance(I) Implementation class is : EurekaServiceInstance(C)
- ${random.value} : It internall executes class: RandomValuePropertySource(C), method: getRandomValue();

- Note: It is generting one hexa-decimal (base-16) value that can be used as id(PK/IDs)\
   ${random.value} sample output: c06725b5295b64b8446dcfb890ffbd1e
- Id generated using = System IP + Date And Time + Random Value These are unique in nature.

- InstanceId is required if you are running your application multiple times (multiple instances). IF there is only one instance then it is not required.

###### 11,12th-November-2021

#### Open Feign (Feign Client)

- It is called as abstract client (or) Declarative Client.
- Here, we need to write interface with abstract methods. Then one class is generated using LoadBalancerClient internally , ie called as Proxy [Dynamic Proxy class].

- Dynamic Proxy class: A dynamic proxy class is a class that implements a list of interfaces specified at runtime (no .class is present/ code generated using Reflection API),class name may look like sun.Proxy$25

- We must add OpenFeign dependency at Consumer code side pom.xml

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

- @EnableFeignClients must be added at Main class to activate proxy class genearation
- For one Consumer, define one interface and apply @FeignClient("serviceId")
- Provide abstract method inside FeignClient interface that matches to Producer RestController (only method and interfaces names can be different).\
  Matching Required: ServiceId, Path, HttpMethod, Parameter and ReturnType.

#### Coding steps

- Define one interface with any name.
- Apply @FeignClient("serviceId") over interface.
- Define one abstract method with any name. But other details must be matched with Producer(ie PATH, Http Method, ReturnType, Parameter)
- FeignClient generates consumer code based on inputs: PATH, HttpMethod, ReturnType, Params(PathVariable/RequestBody)
- Method name is nerver considered for code generation (any method name is fine!!)
- Incase of ReturnType/Parameter is a complex type(classType) then same type must be copied from producer Microservice to Consumer Microservice

###### 12,13th-November-2021

### [Spring Cloud - Config Server](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/MicroServices/Config%20Server)

- In a application there can be multiple Microservices
- In all these Microservices there can be some common KEY=VALs
- These common keys=vals we can define outside of all Microservice apps using Git Vendor (ex: github) and share to every Microservice using one common server "Config Server"
- You just add one Dependency Config Client at Microservice that makes call to Config Server.
- All KEY=VAL finally stored inside memory ie called as "Environment"
- Config server recomanded port number is : 8888 (we can use any)
- \*\*\* When we run Microservice first Config Client only executed gets key=val, merge with Environment and start Microservice Application finally register with eureka.\
  [org.springframework.core.env] Environment(I) Implementation class StandardEnvironment(C)
- Types of Configurations:-
  - External Configuratin : All key-val are kept in Cloud/Internet location example using Github
  - Native Configuration : Storing all key-val inside local system.\
    (Ex: D:/myapp/application.properties , inside project)
- Native Config used only in Dev/Testing Purpose not in Production.

###### 14th-November-2021

### [Distributed Tracing](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/MicroServices/Distributed%20Tracing)

Problem Statement:- Finding Execution Path and Order of Execution of Microservices is complex as they are running at different devices.

- For one request multiple microservices may get executed in connected order (chain of MS in execution)

- In case of Complex design finding Execution Path (TRACE PATH) is not easy as they are running in different locations and multiple instances (isolated components)

- Distributed Tracing Pattern:-

  - Find Execution path of multiple Microservices involved in Request
  - Time taken for a Microservice
  - Helps to find Log line order.

- Spring Cloud : Sleuth and Zipkin

- Sleuth and Zipkin are given by Spring Cloud used for Distributed Tracing.
- Sleuth : Creates TraceId and SpanId to find execution path details and store in temp memory.
- Zipkin Client : It collects data from Sleuth (Sampling) and send to UI (Zipkin Server)

- Zipkin Server : A Central Server that will display all details of execution paths
  - Download the Zipkin Client https://zipkin.io/pages/quickstart.html
  - Run the zipkin jar to start the server by using the command "java -jar {zipkin-server-jar-name}.jar"
  - open the zipkin server at http://localhost:9411/
  - send a http request to the microservice and

##### coding steps:

- adding Sleuth and Zipkin Client
- Creating Smapler object
- Making HTTP call to next Microservice
- Run Zipkin server
- Start all Microservice apps
- execute first Microservice REST Endpoint

**traceId:** Unique number generated to find full path details for a request (including all Microservice executed in order)

**spandId :** To find only one Microservice execution details.

**parentId:** Current Microservice ParentID is taken from previous Microservice spandId

###### 15 to 17th-November-2021

### [Actuator and Admin Server](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/MicroServices/Actuator%20and%20Admin%20Server)

- For any type of Spring Boot applications, we may need some additional services like Beans, info, cache, scheduling, metrics, health,..etc
- Every Service is a pre-defined class called as Endpoint.
- Collection of Endpoints are given as one API known asSpring Boot Actuator.
- Application = Functionality + Additional Services

### REST v/s Endpoint

REST : Set of Annotations and Classes given to implement logic for your application

Endpoint : Set of pre-defined classes used to observe what is happening at application. Services ready to use in Production Environment

**Actuator :-** Production Ready Endpoints

- Endpoint is a pre-defined (class) service used at production environment
- Few ready made services generic for all types of applications.
- Spring Boot Actuator dependency we need to add in Application.
- Actuator gives default 1 service/endpoint here.
- Default base path is /actuator http://localhost:8080/actuator, which can be modified
  "management.endpoints.web.base-path=/myapp",now URL is http://localhost:8080/myapp
- To view all endpoints/services, add in properties "management.endpoints.web.exposure.include=\*"
- Here these are selected and exposed using 'EndpointLinksResolver'.
- We can use this in any type of Spring Boot application (Web, REST, Microservice..etc)
- By default Actuator (Spring boot 2.5.x) is providing only 1 endpoint (health), base path '/actuator'
- Spring boot 2.4.x is providing 2 endpoints default health and info.
- We can activate all or specific endpoints using "management.endpoints.web.exposure.include=\*"
  (or) "management.endpoints.web.exposure.include=beans,info,env"

**beans :** All objects exist at Spring container can be view here\
URL: http://localhost:8080/actuator/beans

Q) Activate All Endpoints and remove beans from it.\
A) management.endpoints.web.exposure.include=\*

Class loaded but not mapped\
management.endpoints.web.exposure.exclude=beans

Class itself not loaded\
management.endpoint.beans.enabled=false

**env :** all KEY=VAL loaded into Spring container Environment memory, can be view here\
URL : http://localhost:8080/actuator/env\
System properties + VM Args + application.properties + ..etc

**schedulers :** To view all schedulings configured , can be view here\
URL: http://localhost:8080/actuator/scheduledtasks

**mappings :** What Path+HttpMethod is mapped with Which class can be found here\
URL: http://localhost:8080/actuator/mappings

**configprops :** KEY=VAL which are loaded using @ConfigurationProperties\
URL: http://localhost:8080/actuator/configprops

**heapdump :** Backup file for current Heap details\
 URL: http://localhost:8080/actuator/heapdump

Visual VM:

- Download: https://visualvm.github.io/download.html
- Extract >> Open ..\visualvm_211\bin\visualvm.exe
- Load heapdump file
- Doc:- https://docs.oracle.com/javase/8/docs/technotes/guides/visualvm/intro.html

**info :** to provide meta-data of application\
URL: http://localhost:8080/actuator/info

###### application.propeties

```textile
info.app.title=ONE
info.app.vendor=NIT
info.app.version=3.2GA
info.app.ver.major=3
info.app.ver.minor=2
info.client.reg=active
info.client.mode=CLOUD
info.client.deploy=true
```

###### 17th-November-2021

### Actuator - Admin Server/Client

**Actuator : Admin Server/Client**

- Actuator has given multiple services to observe operations, but all are manually entered URLs.

- This time Vendor codecentric, has given Client-Server API that automates Request URLs and makes UI for output checking

- At Microservice application we must provide URL of Admin Server "spring.boot.admin.client.url=http://localhost:9999"

**Step 1**\
Create one Common Project For Admin Server\
At main class : @EnableAdminServer\
application.properties

```textile
server.port=9999
```

**Step 2**\
Add Admin Client at every Microservice application

**Step 3**\
add url of admin server in actuator client application/Microservice
application.properties

```textile
spring.boot.admin.client.url=http://localhost:9999
```

###### 18th-November-2021

### Message Queues

**Message Queues :** Message Queues are used to send data from one System (Producer) to another System (Consumer) as Continioues Data Flow.

**Example :**

- Swiggy Deliver Status
- Ola, Uber Cabs
- Live Train Status
- Stock Market data
- Search Engine data with Ads
- Live Circket Score

APIs used for Message Queues

- Basic MQs ( JMS API )
- Advanced MQS ( Kafka API )

##### Message Broker :

- It is a Mediator server that reads Messages from Producer,Stores in a Memory(Destination) and send to consumer.Producer and Consumer both are connected to MOMs Destination only.[MOM - Message Oriented Middleware]

**Communication are two types :** Peer-To-Peer Communication, Publish and Subscribe Communication

**Peer-To-Peer Communication :** If one message is given to one consumer then it is called as P2P(Peer-To-Peer Communication).

**Publish and Subscribe Communication :** If one message is given to multiple Consumers (Cloned Copied) that is called as Pub/Sub.

**Destination :** It is a memory that exist inside MOM software which stores messages given by Producer software.

**Destinations are two types :** Queue ,Topic

**Queue :** It is used incase of P2P(Peer-To-Peer Communication) communication.

**Topic :** It is used for Pub/Sub Communication

#### Apache ActiveMQ 5.x

- Download at https://activemq.apache.org/components/classic/download/
- Click On : Windows apache-activemq-5.16.3-bin.zip
- Extract Here > Open folder
- ..\apache-activemq-5.16.3\bin\win64
- click on activemq bat file
- Starts at : http://127.0.0.1:8161/admin
- Un/pwd : admin/admin
- \*\* To view/modify details
- ..\apache-activemq-5.16.3\conf\users.properties
- Click on Queues/Topics here.
- ctrl+C to stop ActiveMQ

###### 19th-November-2021

#### JMS - Java Message Service

- API Given by Sun Microsystem/Oracle.
- By using this API we can develop MQs.
- Both Producer and Consumer must be Java Application.
- JMS is a Specification. [Interfaces, Annotations]
- JMS vendor is Apache Active MQ API.
- Apache Active = MOM software + JMS Vendor(API) + UI (Web Console)
- We can see details at Web Console(UI) using HTTP protocol at 8161.
- Web Console Port Number : 8161 (HTTP)
- Communication Port Number : 61616 (TCP)
- Connection must be created between Producer/Consumer and MOM software created using ConnectionFactory.
- Producer can send data to MOM software using 'JmsTemplate' (Autoconfigured in Boot)

##### Spring Manual Configuration

> This is auto configured in Spring Boot

```java
@Configuration
public class AppConfig {
	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
		cf.setBrokerURL("tcp://localhost:61616");
		cf.setUserName("admin");
		cf.setPassword("admin");
		return cf;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		return new JmsTemplate(cf());
	}
}
```

- In Spring Boot , autoconfiguration is given for above code, but we need to add "Spring For Apache Active MQ 5" dependency in application

```xml

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
```

- In Properties/YAML file we need to provide

###### application.properties

```properties
spring.activemq.broker-url=tcp://localhost:61616
spring.activemq.user=admin
spring.activemq.password=admin

# Even default is false. Indicates P2P (Peer-To-Peer Communication),
# true indicated pub/sub(Publish and Subscribe Communication)
spring.jms.pub-sub-domain=false

#Destination name
my.app.desti-name=sample
```

###### application.yml

````properties
my:
  app:
    desti-name: sample
spring:
  activemq:
    broker-url: tcp://localhost:61616
    password: admin
    user: admin
  jms:
    pub-sub-domain: false
    ```
````

###### 22nd-November-2021

#### JMS - ActiveMQ API Details

- Sun/Oracle -- 1st Party --- API Specificiation - JMS API

- Vendor\[Apache] -- 3rd Party --- API Implementation -- ActiveMQ API

- Programmer/Company -- 2nd Party -- Applications

###### javax.jms (package)

1. Session (I): Once a connection is created between Producer and MOM
   (or Consumer and MOM) then one Session is created.

2. Message(I)\
   TextMessage(I)\
   To send data from Producer to MOM/MOM to consumer these are used.

###### org.apache.activemq (package)

1. ActiveMQSession(C) : It is Implementation class for Session(I)

2. ActiveMQTextMessage(C) : It is Implementation class for TextMessage(I)

###### org.springframework.jms.core (Spring)

MessageCreator (I)\
createMessage(Session session) : Message\
This interface says use session from container and call method to create Message finally return same.

###### To Convert P2P Code into Pub/Sub Concept, just modify spring.jms.pub-sub-domain=true in application.properties file

###### 23rd-November-2021

#### Limitations of JMS/ActiveMQ:-

- No Load Balancer. There is single MOM software exist. In case of multiple no.of consumers/producers are added, then MOM works very slow.
- No Data partitions (for large data trasfer) : Data is sent without any serailization and no data partitions.
- Language Dependent, works only for Java Producer/Consumers.
- It is protocol dependent, work only using TCP.

#### Apache Kafka S/w

- It is Open Source Messages Queue API used between multiple applications.
- It follows Multi-Borker Concept, to handle no.of consumer and message trasfer.
- Any language Applications can be integrated.
- It is protocol independent. (HTTP,TCP...)
- Large data is send in partitions (Data Packets)
- Data is sent from Producer in Serialized again at consumer it is deserialized.
- Here, only Topic Memory exist. By using this we can send data to both one or multiple consumers.
- Topics Section stores data based on KEY(topicName) which is controlled by Apache ZooKeeper.
- One Message Broker reads data from Topic Section and send it to consumer.
- There can be multiple topics Created inside Topics section.
- Producer will send data in KEY=VAL Format (Data is Serialized) at Consumer side Data is DeSerialized.
- Consumer and Broker are connected using topicName only.There is no direct communication b/w producer and conusmer.
- Cluster = Group of Message Brokers. When we start Kafka S/w it is started with 1 Message broker only.
- EcoSystem = Topics + Cluster + Zookeeper

Download Kafka S/w

1. Goto : https://www.apache.org/dyn/closer.cgi?path=/kafka/3.0.0/kafka_2.12-3.0.0.tgz

2. click on : HTTP
   (2.8.0 )\
    Released April 19, 2021\
    Binary downloads:\
    Scala 2.12 - kafka_2.12-2.8.0.tgz (asc, sha512)

3. Extract : kafka_2.12-3.0.0.tgz
4. Copy to root folder or C:/drive
5. Open Cmd in basePath/root folder\
   C:\kafka_2.12-3.0.0>

For other OS:
https://kafka.apache.org/quickstart

##### Execute Commands

a. Zookeeper

```properties
C:\kafka_2.12-2.8.0>
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

It runs on port : 2181

b. Kafka Server

```properties
C:\kafka_2.12-2.8.0>
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

It runs on port : 9092

c. Topic

```properties
C:\kafka_2.12-2.8.0>
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic sample
```

d. Producer console

```properties
C:\kafka_2.12-2.8.0>
.\bin\windows\kafka-console-producer.bat --bootstrap-server localhost:9092 --topic sample
```

e. Consumer console

```properties
.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic sample --from-beginning
```

###### 24th-November-2021

**Topic :** It is created inside Kafka S/w using details like Name, replication-factor(=no.of Consumer), partitions size (1)

**Replication-factor :** replication-factor means creating no.of copies of actual message to be delivered to consumer. If we did not specify (then no.of consumers connected while running).

**partitions size :** Data is converted into packets internally and stored with index numbers (offset)

###### 25th-November-2021

### [Integration : Spring Boot + Apache kafka](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/MicroServices/Message%20Queues/Apache%20Kafka)

- Replication-Factor is used to specify no.of copies to be created and delivered to no.of consumers.\
  _Ex :_ Replication-Factor=4, then actual message 4 copies created and given to 4 consumers.

- Replication-Factor is not provided then based on no.of consumers connected to kafka before sending message to such topic is taken as Replication-Factor internally. But, all these consumer should maintain one common word ie groupId.\
  _Ex :_ C1(groupId=ABC), C2(groupId=ABC), C3(groupId=ABC), C4(groupId=MNO) then topic=Sample, connected groupId request for 3 copies of actual message.

- KafkaTemplate<K,V> is a auto-configured class given by Spring For Apache kafka
  used to send data from producer application to kafka s/w using topicname
  and sends in Serialized Format.

- @KafkaListener() is used at consumer application. To read data based on
  topicName and groupId.

- ToDo Serialization and Deserialization, Kafka API has given classes.

```properties
my:
  app:
    tpc-name: common-data
server:
  port: 8080
spring:
  kafka:
    consumer:
      bootstrap-servers: localhost:9092
      group-id: sample
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer

    producer:
      bootstrap-servers: localhost:9092
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/boot9am
    username: test
    password: root
  jpa:
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL8Dialect
    hibernate:
      ddl-auto: create
```

###### 26th November 2021

### [Spring Cloud API Gateway](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/MicroServices/API%20Gateway)

- There are multiple components in Microservice Design and all running at different locations (IPs/Ports).

- There must be one common and entry and exit point for our Microservice setup. ie Gateway (Spring Cloud Gateway)

- Gateway is used for

  1.  Routing : Executing a Microservice when request made by client.

      - Static Routing : (single Microservice instance), Gateway makes a direct call to Microservice
      - Dynamic Routing: (Multiple Microservice instances), gateway goes to eureka to fetch A ServiceInstance having less load factor.

  1.  Filters : Modify Request/Response Sections, Adding Header, Req Params..etc

  1.  Security :- We can implement Token based Security (JWT/Oauth/3rd party)
      (ReST is stateless, No HttpSession)

- For a Reuqest it will pass API Gateway once, if there is Microservice Intracommunication then no need to go through API gateway again.
- Gatway makes request to Microservice, Eureka never makes any request to Microservice.
- Even Gateway App must be register with Eureka, like other Microservice apps.
- API Gateway also one type of Microservice, that can make call to any Microservice in App, using eureka it gets serviceInstance data.
- Gateway Application contains HandlerService that chooses one ServiceId[Dynamic Routing]/URL[static rounting] of Microservice based on Path Condition(Predicate).
- There are optional Filters used. Pre-Filter executed before sending request to Microservice and Post-Filter is executed after getting response from Microservice.\
  Pre-Filter --> works on Request\
  Post-Filter--> Works on Response

      Routing Configuration using Spring Cloud Java Config

**route :** Navigating to a Microservice when a request comes to Gateway.

- A Route must have Predicate(Condition, Path), URI(static/lb), Filters(optional) all are identified using one unique ID.
- 1 RouteDefinition = 1 Id ( Predicate + URI + Fitlers ) [For one Microservice app]
- Java Configuration is the mostly used configuration in API Gateway.
- RouteLocator (List<Routes>) => Builder class => Route Obj (Id,Path,URI, Filters).

```java
@Configuration
public class SpringCloudGatewayConfig {
	@Bean
	public RouteLocator configRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(
						"employeeServiceId", //ID
						r->r.path("/employee/**") //Predicate(Path)
						.uri("lb://EMPLOYEE-SERVICE") //URI
						)
				.route(
						"productServiceId",
						r->r.path("/product/**")
						.uri("lb://PRODUCT-SERVICE")
						)
				.build()
				;
	}
}
```

```java
RouteLocatorBuilder(C) {
 Builder routes() {}
}

class Builder {
  List<Buildable<Route>> routes;
}

public interface Buildable<T> {
	T build();
}

class Route {
  id, Predicate, URI, filters
}
```

##### For static routing

- Do not use lb://Service-id in case of Single Instance.Provide direct URL of Microservice (http://IP:PORT).
- Request Path must be matching with Controller path
- Here /\*\* indicates any additional path with base.

```java
package in.nareshit.raghu.config;
@Configuration
public class SpringCloudGatewayConfig {
	@Bean
	public RouteLocator configRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(
						"employeeServiceId", //ID
						r->r.path("/employee/**") //Predicate(Path)
						.uri("http://localhost:9900") //URI
						)
				.route(
						"productServiceId", //ID
						r->r.path("/product/**") //Predicate(Path)
						.uri("http://localhost:8080") //URI
						)
				.build();
	}
}
```
