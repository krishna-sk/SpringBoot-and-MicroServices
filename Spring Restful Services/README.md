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
