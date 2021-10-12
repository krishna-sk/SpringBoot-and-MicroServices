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
