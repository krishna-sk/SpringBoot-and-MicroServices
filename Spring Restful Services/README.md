# Spring Restful Services

- Webservices
- Rest webservices
- REST APIs and Endpoints
- Consumer Applications
- Testing(POSTMAN/Swagger/JUnit)
- Microservice(MS#) Introduction
- Patterns used in MS#

#### Webservices :-

###### 12th October 2021

- It is a process of Integration of applications (any language/any technology)
- Linking Two different projects which are running in two different servers.
- By using Webservices we are not developing applications. We are just linking/integrate applications.
- ToDo Integration using 4th layer IL - Integration Layer. This layer communicates with Service Layer of same project and IL of another Project
- Webservices is implemented using HTTP protocol and data is exchanged using XML/JSON Formats.
- Here, two applications required for Webservices, they are Producer (Service Provider) and Consumer (Service Reader).\
  **Ex:**\
  Loading Amount to PayTm using ICICI-Bank\
  Producer = ICICI Bank , Consumer = PayTM\

  Do payment using PayTM after Selecting Movie Ticket in BookMyShow\
  Producer = PayTM , Consumer = BookMyShow

- One application may behaves as either producer or consumer (even both based on requirement).
- There can be multiple producer and consumers exist in network. But atleast 2 apps (1 producer and 1 consumer) required for integration.
- Consumer and Producer might have implemented in different languages. In that case data is exchanged using Global Data Formats(XML/JSON).
- Java Object can be converted into JSON Format even reverse is possible using JACKSON API.
- Java Object can be converted into XML Format even reverse is possible using JAXB API [Java Architecture for XML Binding].

#### JSON - Java Script Object Notation

- It is a Object is java-script language.
- But later used in Webservices Concept.
- It is light weight (less memory) compared to Programming language obejcts
- it is global in format.(Every language can read it)
  **Syntax:**\

```json
 {
 "key" : value, "key" : value, ....
 }
```

- Key must be placed in quotes , value is quoted only if String type.
  **ex:**

  ```json
  {
    "eid": 101,
    "ename": "ABC",
    "esal": 500.0
  }
  ```

- When we add Spring Web Dependency in Boot applications, implicite conversions for Object--JSON

#### Spring Boot REST Design

###### 14th-October-2021

#### ReST -

- Two applications are connected using Standard Protocol with Global Data formats that makes application Integration. Such architecture is called as ReST.\
   **Re \- Representation (Global Format)**\
   **S \- State (Date)**\
   **T \- Trasfer (Exchange)**
- HTTP + JSON/XML --- combination is used.
- Two applications are used.\
   a. Producer ( Rest never used to design UI part)\
   b. Consumer ( access producer/may contain UI )

- RestControllers are even like normal Controllers but never connected with any UI (View or ViewResolvers). It Returns Response back to consumer\
  **@RestController = @Controller + @ResponseBody**
- Return Types can be String, ClassType/Custom Type, Collection, ResponseEntity\<T\>
- If ReturnType is non-String type, Then it will be converted into JSON/XML.
- All Support all HTTP methods\
   GET : To fetch Resource from Producer to Consumer\
   POST : To create new Resource at Producer\
   PUT : To Modify existed Resource at Producer\
   DELETE : To Remove existed Resource at Producer\
   PATCH : To partially update Resource at Producer\

  **Database Operations --- HTTP Protocol**\
   INSERT --- POST\
   UPDATE --- PUT/PATCH\
   DELETE --- DELETE\
   SELECT --- GET

- HTTP Methods are recomanded standards. If they used in wrong way still you may get output. They are like contract b/w Producer and Consumer.

**ResponseEntity\<T\>:** ResponseEntity<T> is a final Response given by Producer app, Which mainly contains Data(Body) and Http Status along with Headers..etc

- ResponseEntity later converted into JSON/XML format (For non-String).\
  **Http Status (5)**\
   1xx -------------- Informational\
   2xx -------------- Success\
   3xx -------------- Redirect\
   4xx -------------- Client Side Error\
   5xx -------------- Server Side Error\
- Http Status are common codes used to indicate current status of request process for a consumer given by Producer.

**POSTMAN :** It is a testing tool used to make HTTP Request that support all types of Http method.

##### RestController class

```java

package com.demo.rest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/employee")
public class EmployeeRestController {
    @PostMapping("/save")
    public ResponseEntity < String > createEmployee() {
        return new ResponseEntity < String > ("CREATED!", HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity < String > fetchEmployee() {
        return new ResponseEntity < String > ("FECTHING!", HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity < String > updateEmployee() {
        return new ResponseEntity < String > ("UPDATED!", HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity < String > deleteEmployee() {
        return new ResponseEntity < String > ("REMOVED!", HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity < String > updateEmployeeEmail() {
        return new ResponseEntity < String > ("UPDATED-PATCH!", HttpStatus.OK);
    }
}

```

#### MediaType Annotations

###### 18th-October-2021

- Data Exchange for Global Formats like XML/JSON can be automated using MediaType annotations.\
  ie Java Object <-----> XML/JSON
- @RequestBody : (input/ Method Parameter)\
  This annotation reads HTTP Request Body Section (XML/JSON) and converts into Object Format. This object is given as method parameter.
- @ResponseBody : (output/ ReturnType)\
  Reads ReturnType (object) and converts into XML/JSON this data is placed into Http Response Body Section.
- @RestController = @Controller + @ResponseBody\
  ie we need not to apply @ResponseBody externally.
- Content-Type is used to indicate what type of data exist in current (request/response) body.
- Above annotations works for non-String types (ClassType/Collection type) only. In case of String, content-type is plain text (text/plain) No JSON/XML Conversion is done.
- By default Spring boot supports only JSON Format Output. To get XML Output, add below dependency in pom.xml, run application, while making request send Header Param [Accept = application/xml] output will be XML Output (or) 406 Not Acceptable

```xml
    <dependency>
     <groupId>com.fasterxml.jackson.dataformat</groupId>
     <artifactId>jackson-dataformat-xml</artifactId>
    </dependency>
```

**Web MVC :** @ModelAttribute : HTML Form ---> Object ---> Controller#Method Input\
**Rest API :** @RequestBody : XML/JSON ----> Object --> RestController#Method Input

###### Note:

- If XML Input is not supported (@RequestBody not supporting) then Http Status is : 415 Unsupported MediaTypes.
- If XML Output is not supported (@ResponseBody not supporting) then Http Status is : 406 Not Acceptable
- If input (@RequestBody) not having valid XML/JSON Data then 400 BAD REQUEST
- Sending empty JSON {} or XML \<Object\>\</Object\> is valid.
- It creates object using default constrcutor.

#### Path variables

- Request Params : to send primitives data using URL to application\
  Syntax: URL?key=val&key=val&key=val
- Path Variables : to send primitives data using URL to application\
  Syntax: URL/data/data
- Clean URL : No symbols used like ? = &
- URL length is reduced compared to Request Params
- Directly data is sent as Path, no key is required while sending.\
  ex: http://localhost:8080/emp/101/ABC
- Order must be followed (in req Param-- no order is required)
- **Path :-** Path indicates Resource(Code) identity used by consumer to access it.

- static path (regular path) : A Direct path given to a resource\
   ex: /show, /find, /save..etc
- dynamic path (data ) : Data/value comes at runtime\
   Syntax in code: /{key}

- Dynamic path indicates some data must be sent using URL.
  Such data we have to read in code using annotation:
  @PathVariable DataType keyName
- if we pass less or more no.of Path variables , front controller Returns 404-Not found. Paths (Levels) Count must be matching.
- IF data type is mismatch then , front controller Returns 400-Bad Request.

##### Controller Class

```java
package com.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {
    @GetMapping("/find/{id}/{code}")
    public String showData(
        @PathVariable Integer id,
        @PathVariable String code
    ) {
        return "HELLO => " + id + "-" + code;
    }
}
```

##### Output

```text
http://localhost:8080/employee/find
Output: 404 - Not Found

http://localhost:8080/employee/find/10/SAM
Output: HELLO => 10-SAM

http://localhost:8080/employee/find/SAM/10
Output: 400 - Bad Request

http://localhost:8080/employee/find/10
Output: 404 - Not Found

http://localhost:8080/employee/find/10/SAM/200.0
Output: 404 - Not Found
```

##### Controller Class

```java
package com.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {
    @GetMapping("/find/code")
    public String showDataA() {
        return "HELLO STATIC ";
    }
    @GetMapping("/find/{code}")
    public String showDataB(
        @PathVariable String code
    ) {
        return "HELLO => " + code;
    }
}
```

- If Request URL is matching for both static paths and dynamic paths then priority is given to static paths.

##### Output

```text
http://localhost:8080/employee/find/ABCD
Output: HELLO => ABCD

http://localhost:8080/employee/find/code
Output: HELLO STATIC
```

- If multiple methods are having same static path and dynamic path count matching then it is : IllegalStateException: Ambiguous handler methods mapped
  for '/employee/find/AAA':\
  EmployeeRestController.showDataA(String),\
  EmployeeRestController.showDataB(String)}

---

Case-study:-\
m1() -- /home/find/test\
m2() -- /home/find/{test}\
m3() -- /home/{find}/{test}\
m4() -- /{home}/{find}/{test}

**Req#1** : .../home/home/home\
 Matching :m3(), m4()\
 selected: m3()

**Req#2** :.../home/find/200.0
Matching : m2(), m3(), m4()\
 Selected : m2()

**Req#3** : .../home/Find/test
Matching : m3(), m4()\
 selected : m3()

##### Controller Class

```java
package com.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/employee")
public class EmployeeRestController {
    @GetMapping("/find/grade/code")
    public String showDataA() {
        return "HELLO :: A ";
    }
    @GetMapping("/find/grade/{code}")
    public String showDataB(
        @PathVariable String code
    ) {
        return "HELLO :: B " + code;
    }
    @GetMapping("/find/{grade}/{code}")
    public String showDataC(
        @PathVariable String grade,
        @PathVariable String code
    ) {
        return "HELLO :: C " + grade + "-" + code;
    }
}

```

##### Output

```text
http://localhost:8080/employee/find/grade/code
Output: HELLO :: A

http://localhost:8080/employee/find/grade/ABC
Output: HELLO :: B ABC

http://localhost:8080/employee/find/ABC/grade
Output: HELLO :: C ABC grade
```

**Possible Return Types for RestController methods:**

- String, ClassType/Custom Type, Collection Type...
- But mostly used format is:-\
  ResponseEntity<T> = Body + Http Status + Header Params
  [ it is equal to creating : HTTP Response ]
- HttpStatus : it is enum , given by HTTP protocol to indicates universal codes, for every programming language.
- Every Response must have Http Status. No default value.
  On success : 200 OK, Consumer Data Wrong : 400 BAD REQUEST,
  Server problem/exception : 500 INTERNAL SERVER ERROR...etc
- Http Headers (Can be pre-defined/user-defined too) indicates task/input/action to producer/consumer. Data must be passed using key=val in header section.\
  ex:\
   Content-Type : application/json\
   Refresh : 5; google.com\
   Content-Length : 500 ...etc

###### 25-October-2021

**Serializable(I)** : To write an object to a network/stream/file such classType must be Serializable. Incase Serializable(I) recommended to give id for every class using serialVersionUID. if not provided JVM allocates default.

**@Transactional :** for pre-defined repository methods, need not to define externally. For custom non-select operations you must provide this.

- @Transactional: Will beginTx and commit() if method/Query is success. rollback() if any exception.
- 1 Endpoint - Define one method for one Operation in RestController
- 1 Rest API - Define full class (RestController) with multiple Endpoints.
- For Consumer We provide Endpoint details -- URL, HttpMethod, Input/Output Header details..etc
- @ModelAttribute : Form ---> Object (given as Input)
- @RequestBody : XML/JSON --> Object (given as Input)
- 200 OK : Http status indicates Request process is successful.
- 201 CREATED: Status indicates Request process is successful + a new Resource is created at Producer/Server side.[ Resource : file/DB/... ]
- Symbol ? (wildcard char) indicates data-type for Generic is decided at runtime.
- For ReturnType non-String(List/Product..etc) then @RestController provides @ResponseBody, that converts data into JSON format.

###### 26-October-2021

**Update Operations:-**

- Read JSON/XML data for Product Object.
- This time Object must have ID(PK) also.
- If given id is null or not exist then we throw Exception Else update operation is executed.
- Custom-Non SELECT Operation, then must provide @Modifying + @Query("") at Repository
- Only @Query behaves like SELECT operation @Query() + @Modifying indicates to Data JPA "this is NON-SELECT".
- @Transactional: Will beginTx and commit() if method/Query is success. rollback() if any exception.

#### Swagger UI

- SpringFox Company has provided Dyanmic Test UI for all Rest Controllers.
- By using this open Source UI, we can generate Test screen for all our operations which reduces entering URL manually, select Method Types, input checking..etc
- Easy to test
- We can view all operations in code
- Easy for API Overview.

###### Step 1 : Add below Dependencies

```xml
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.9.2</version>
		</dependency>

		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.9.2</version>
		</dependency>

```

###### Step 2 : Write Configuration Code

a .Create Empty Docket\
 new Docket(DocumentationType.SWAGGER_2)

b. Select all Rest Controllers from project using one common package name\
.select()\
.apis(RequestHandlerSelectors.basePackage("in.nareshit.raghu.rest"))

c. Provide condition having one common path (ex: /rest/\_\_\_)\
.paths(PathSelectors.regex("/rest.\*"))

d. Create Full Docket.\
.build();

e. Run application and enter URL:
http://localhost:9090/swagger-ui.html

AT class Level : @Api(description = "TEST PRODUCT OPERATION")\
AT method level : @ApiOperation("FETCH ALL ROWS AS LIST")\
To avoid display : @ApiIgnore\

###### 27-October-2021

#### Error and Exception Handling Flow

- Spring boot by default given one class 'BasicErrorController' and method error() for error and exception handling.

- But this time we want to create our own customized Exception class then define one class with any name and add @RestControllerAdvice\
  @RestControllerAdvice = In @RestController any Exception (AfterThrowing Advice)

- To indicate every exception type define one method, using annotation @ExceptionHandler(T.class) [T=ExceptionClass]

- @RestControllerAdvice internally follows @ResponseBody, if returntype is complexType(class/array) then it is converted into JSON Format.

#### logging

- Lombok annotation: @Slf4j generate Logger object at class private static final Logger log = LoggerFactory.getLogger(ProductRestController.class);

- Spring boot default Priority method is set to INFO.\
  FATAL\
  ERROR\
  WARN\
  INFO\
  DEBUG\
  TRACE

- SLF4J simplifies Logging (Log4J) Concept, need not to define log4j.properties, common Logger object created. root logger is set to INFO.

```yml
logging:
  file:
    name: C:/Users/krish/mylogs/abcd.log
    #default size is 10 MB
    max-size: 20MB
    #default history is 7 days
    max-history: 15
    #default level is info
  level: 
    '[com.krishna]': DEBUG
  pattern:
    file: "%d{yyyy-MM-dd HH:mm:ss:SSS} %-5level--- %C [%M] - %msg%n"
    console: "%clr(%d{yyyy-MM-dd HH:mm:ss:SSS}){magenta} %clr(%-5level)--- %clr(%C){yellow} %clr([%M]){cyan} - %msg%n"
```

###### 28-October-2021

#### Spring Boot UnitTesting

- By default Spring Boot adds : spring-boot-starter-test dependency JUnit 5.x [ junit-jupiter ] + Mockito 3.x [mockito-core]
- Even Integration jar: mockito-junit-jupiter
- JUnit assert Results. ie compare actual result with expected values.
- Mockito creates required objects to run unit testing.[dummy container, request, DB connection, service object..etc]

- Incase of Unit testing we do not run main() class. We will run Unit testcase. So, full container setup must be done using Mockito.

1. Create Server setup with Empty container

```java
   @SpringBootTest(webEnvironment = WebEnvironment.MOCK)
```

2. Load all parent project objects and your classes obejcts into container

```java
   @AutoConfigureMockMvc
```

3. Load Properties file if you have your own configuration.

```java
   @TestPropertySource("classpath:application-test.properties")
```

4. Refer this to Spring container and read into variable.

```java
   @Autowired
   private MockMvc mockMvc;
```

5. For Every method testing\
   I. create one Request using Mocking\
   II. Execute Request and get Result\
   III. Read Response From Result\
   IV. Assert Result using JUnit.

- Use H2 (Inmemory) Database for testing purpose.

1. Comment MySQL Dependency
2. Add H2 Database Dependency in pom.xml

```xml
   <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <scope>runtime</scope>
   </dependency>
```

3. No keys required (Driver, URL, UN, PWD and DIALECT)
4. Default Hibernate.hbm2ddl.auto=CREATE-DROP

###### 02 November 2021

#### Connection Pooling :

Pool : Group of Objects (of same classType)\
ex: Employee Pool = Employee class obejcts (e1,e3,emp...)

- String Constants Pool (SCP)
  Every String object is a Constant.\
   Group of String objects is called as SCP.

**Connection Pooling :** Group of Connection Objects which are created while starting/running application.

- To send one SQL to DB/to get Result from DB we need Database Connection.
- JDBC Sample code for Database Connection

```textile
  [java.sql]
  Class.forName(driverClass);
  Connection con = DriverManager.getConnection(url,un,pwd);
```

- One Connection object is fine to Perform Database Operations.\
  [Recomanded for small scale application/single user apps] But, incase of Multi-user application connection pooling is required.
- Connection pools makes NIO calls(execution of SQLs/Result trasfer) makes
  faster.
- Connection pool is created with group of Connection objects, app will do
  - Read Connection From Pool
  - Execute task (send/receive)
  - Return Connection to Pool
- In Spring Boot Connection pool creation/handling is Auto-Configured. We can customize the details.

- When we add Spring Boot Data JPA Dependency, by default "HikariCP" [Hikari Connection Pool] is added to Project.

```xml
   <dependency>
      <groupId>com.zaxxer</groupId>
      <artifactId>HikariCP</artifactId>
      <version>4.0.3</version>
      <scope>compile</scope>
    </dependency>
```

- All Default configurations are given inside class : HikariConfig [com.zaxxer.hikari]

- This HikariCP is a open source JAVA API. Not from Spring Boot. It is 3rd party {not from Sun/Oracle, or from Spring Boot}

- It is mainly designed for JDBC connection pool concept.

- One Connection Cycle is defined as single DataSource.getConnection()/Connection.close().

- One Statement Cycle is defined as single Connection.prepareStatement(), Statement.execute(), Statement.close().

- Comapred to other CP(Connection Pool) providers faster in execution process.

- When all connection objects are busy, 1 request is waiting for one connection more then X-time (ex: 30000 MillSec) then it is sent Response as Connection Timeout.

- We can define no.of Max Conections created at Pool (Even H/w Config required)

- if there are no connections in use then keep few connections active,close other (minimum-idle)

- max-lifetime (recomanded as -1 for server start to stop).To keep a connection in pool for a period of time.

- DEFAULT_POOL_SIZE is 10.

```textile
spring.datasource.hikari.pool-name=My-CP
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.minimum-idle=15
spring.datasource.hikari.idle-timeout=10000
spring.datasource.hikari.max-lifetime=60000
spring.datasource.hikari.maximum-pool-size=20
```

## Rest Template

###### 05-November-2021

### Consumer using Boot - RestTemplate(C)

- To implement Producer application we can use RestController, Get/POST Mappings ResponseEntity..etc

- Consumer application can also be implemented using Boot. RestTemplate(C) [Http client class]
- This class : RestTemplate(C) is used to make HTTP calls [GET/POST/PUT..]
- This RestTemplate needs inputs like : URL, PathVariables, Header Params, Body, ResponseType..etc
- RestTemplate(C) will never comes with Auto-configuration by Spring boot. We must create this object manually.
- Consumer also runs in server, to run this application multiple times, for testing, stop it and run again (or use System.exit(0))

**getForEntity(URL, ResponseType, Pathvariables..) : ResponseEntity<T>** : This method is used to make HTTP call (GET) type to producer application and gets Response Back into ResponseEntity<T> Object.

- Recommended ResponseType for any combination is : String.class. It can store text/XML/JSON..etc
- Even we can use Our Own ResponseTypes ex: Employee.class, Product.class..etc. If Response is JSON/XML then it is converted into Object and given to consumer.

**postForEntity(url,reqestEntity,ResponseType,PathVariables...):ResponseEntity<T>** : This method is used to make POST method call.

- it supports passing RequestBody + headers using reqestEntity concept.
- Main code is:

```java
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);
String body = "{\"id\":101,\"name\":\"SAM\",\"sal\":250.0}";
HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
```

- put() and delete() are given by RestTemplate(C) [works fine] but returns void,no ResponseType is given. So, recomanded new method: exchange()

- exchange method is given by RestTemplate(C) used to make any type of HTTP call (GET/POST/PUT/DELETE..etc)

```java
exchange(
     String url,
     HttpMethod method,
     HttpEntity<?> requestEntity,
     Class<T> responseType,
     Object... uriVariables
  ) : ResponseEntity<T>
```

- Here HttpMethod is a enum.
- RequestEntity can be null some time (for GET/DELETE methods)
- ResponseEntity is must ex: String, Employee,..etc
- uriVariables/PathVariables are optional to pass(based on producer URL)
