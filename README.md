# SpringBoot-and-MicroServices

### Table of Contents

- [ComponentScan](#componentscan)
- [@Value, properties file](#value-properties-file)
- [Project Lombok API](#project-lombok-api)
- [CommandLineRunner & ApplicationRunner](#commandlinerunner--applicationrunner)
- [@ConfigurationProperties](#configurationproperties)
- [@Inputs to Application , Option Args](#inputs-to-application--option-args)
- [Spring Boot Profiles Using Inputs (Properties/YAML)](#spring-boot-profiles-using-inputs-propertiesyaml)
- [Spring Boot Profiles Using @Profile](#spring-boot-profiles-using-profile)
- [Spring Boot Stop Watch, Banner and VM args](#spring-boot-stop-watch-banner-and-vm-args)
- [Spring Boot Email Programming part-1](#spring-boot-email-programming-part-1)
- [Spring Boot Email Programming part-2](#spring-boot-email-programming-part-2)
- [Spring Boot Scheduling](#spring-boot-scheduling)
- [Hibernate with JPA](#hibernate-with-jpa)
- [Spring Boot Data JPA](#spring-boot-data-jpa)
- [Spring Boot Data JPA - Association Mapping](#spring-boot-data-jpa--association-mapping)
- [Spring Boot Web Mvc](#spring-boot-web-mvc)

## [@ComponentScan](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/ComponentScan)

###### 22nd July 2021

- All programmer defined files/classes must be under Starter class/Main class package (or its sub-package).

- If your create your class outside of Main class package (or any diff package)
  then Spring container can not find your class, no object is created, so to resolve this error we use **@ComponentScan** on top of main class.

## [@Value, properties file](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/ValueAnnotation%20and%20PropertiesFile)

###### 23rd July 2021

- Spring Boot creates and loads a file **application.properties** by default.

- Read data from properties file for our own keys using **@Value("${key}")**

- Create custom properties file and load it into the container

## [Project Lombok API](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Project%20Lombok%20API)

###### 26th, 27th, 28th July 2021

- This is Open Source java API, used to generate source code before compile

- Annotations

  - @Getter

  - @Setter

  - @ToString

  - @AllArgsConstructor

  - @NoArgsConstructor

  - @RequiredArgsConstructor

  - @EqualsAndHashCode

  - @Data

## [CommandLineRunner & ApplicationRunner](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/CommandLineRunner%20and%20ApplicationRunner)

###### 31st July, 6th August 2021

- Runner : It is a block of code that gets executed only once when application is started.\
  Ex:
  Test Our Logic, CRUD Operation Test

- If we define multiple Runners in Spring Boot application they are executed in Unicode naming order.(A-Z order)

- UnOrdered Runners are executed at last with naming rule and with last priority.

- we can read data from Spring Container to our code using

  1. @Value (in case of Annotation Configuration)
  2. Environment Autowired , env.getProperty() (in case of Java Configuration)
  3. @ConfigurationProperties (For Spring Boot)

## [@ConfigurationProperties](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/ConfigurationProperties)

###### 2nd,3rd,4th,5th August 2021

- @ConfigurationProperties is called as Bulk Load.
- It supports reading multiple keys data into variables by defining this annotation only once at class level.

## [Inputs to Application , Option Args](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Inputs%20to%20Application%20and%20Option%20Args)

###### 7th August 2021

- Priority Order

  1. Option args (Commands Line Args) (--key=val)
  2. VM Args
  3. application.properties
  4. application.yml

- What ever process we use, finally we can read data using @Value or @ConfigurationProperties only.

## [Spring Boot Profiles Using Inputs (Properties/YAML)](<https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Spring%20Boot%20Profiles%20Using%20Inputs%20(Properties%20and%20YAML)>)

###### 9th August 2021

- Create application.properties for different profiles using naming convention **application-profileName.properties**

- Create application.yml for different profiles in single file and specify the profiles using

  ```textile
     spring:
       config:
         activate:
           on-profile:
             - profileName
  ```

- set active profiles while running the application using **--spring.profiles.active=profileName** in the run configuration program arguments

## [Spring Boot Profiles Using @Profile](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Spring%20Boot%20Profiles%20Using%20Profile%20Annotation)

###### 10th August 2021

- To execute class/code based on environment then use **@Profile** on top of class.
- A class has no @Profile annotation means execute logic in any environment.\
  Ex:

  1. SwaggerUi (RestWebservices Test Screen) -- @Profile({"default","qa"})
  1. RoleDataInsert (QA) -- @Profile("qa")
  1. GoogleMailService -- @Profile("prod")

- @Profile can be placed at any class.
- If current profile is matched with @Profile then code will be executed.
- We can provide multiple profile names by using @Profile({"profile-1","profile-2"})
- A class which has no @Profile annotation is executed in every environment.

### If No active profile is set, then default profile is active.

## [Spring Boot Stop Watch, Banner and VM args](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Spring%20Boot%20Stop%20Watch-Banner-VM%20Args)

###### 12th August 2021

## StopWatch (C) :

This is given by Spring f/w used to calculate time taken for a process like method execution time, block execution time..etc\
**Methods** : start(), stop()

## Banner :

When we start spring boot application at console Spring LOGO is printed that is called as banner.

- To disable this we use **spring.main.banner.mode=off**
- we can even print out own banner by using this [link](https://devops.datenkollektiv.de/banner.txt/index.html), generate text and place the content in **banner.txt** under src/main/resources folder
- To modify file name from banner.txt to any other we should use key=val that is spring.banner.location=classpath:sample.txt

## VM Arguments:

A variable created at JVM level to provide common data to multiple application/VM as input in key=val format.

- To create this\
  Syntax : **-Dkey=val**
- To read data

```textile
String value = System.getProperty("key");
```

- VM args are used for JVM inputs, less used in production.apps. But still Spring boot supports as 2nd priority input.

## [Spring Boot Email Programming part-1](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Spring%20Boot%20Email%20%20Programming%20Part-1)

###### 16th August 2021

#### application.properties

```textile
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=username
spring.mail.password=password
spring.mail.properties.mail.smtp.starttls.enable=true
```

#### Enable Less secure app

- Create new Gmail Account
- Login and Click on Profile Name (Top right corner)
- Manage Your google Account
- Click on Security
- Enable Less Secure Apps
- Turn it on.

## [Spring Boot Email Programming part-2](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Spring%20Boot%20Email%20%20Programming%20Part-2)

###### 17th August 2021

#### Sending Mail as Text (or) HTML :-

- Here setText() is overloaded
- setText(text) internally calling setText(text,false)
- setText(String , boolean) is the actual method contains logic to set data.
- if boolean html=false, Text data is sent as Plain Text.
  html=true , Text data is sent as HTML Content.

#### Working with Multiple Attachments :-

- Resource(I) : it indicates a file exist in a location.
- A File exist in your System (D:/abcd folder ex) ==> FileSystemResource()
- A File Exist in internet location ==> UrlResource()
- A File Exist in Project src/main/resources folder ==> ClassPathResource() ==> Use Resource Parameter as Array

#### Overloading

- using same method name and but different in paremeters.
- send() method is overloaded as
  - to,subject,text (find in SimpleEmailRunner)
  - to,subject,text,file (find in EmailWithOneAttachmentRunner)
  - to,subject,text,cc,bcc,files (find in FullRunner)

## [Spring Boot Scheduling](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Scheduling)

###### 18th,20th,21st August 2021

#### Scheduling:-

Executing a task/method in a loop based on period of time or point of time.

Examples:-

- Bank account statement (Every for month)
- CC Bill Statement
- Weekly/Monthly/3months Reports
- EMI Reminder
- Birthday Messages
- Tax Payment
- Electricity Bill Payment
- Server reports/Log files/Billing...etc

#### period of time : Time gap

4hrs, 2mins, 5days, 6years

#### point of time : Exact start date/time

1st Jan- 9AM, 31st Dec-6PM ..etc

## [Hibernate with JPA](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Hibernate%20with%20JPA)

###### 24th August 2021

#### Hibernate:-

1. Entity Class
1. Configuration XML
1. Test/Main class

**Entity Class :** A class that is mapped with Database table using JPA Annotations. (@Entity, @Table, @Id, @Column ..etc)

```java
@Entity
@Table(name="stdtab")
class Student {
 @Id
 @Column(name="sid")
 Integer stdId;
 @Column(name="sname")
 String stdName;
 @Column(name="sfee")
 Double stdFee;
}
```

**@Entity :** Must be applied on top of class, that maps class with table and variable with columns.\
**@Id :** It indicates Primary Key, it must be applied over a variable.\
**@Table :** It is optional. If we do not provide tableName, then class name is taken as tableName.\
**@Column :** It is optional. If we do not provide ColumnName, then variable name is taken as ColumnName.

- Hibernate Initial Releases are not implementation of JPA. They are just ORM implementation.
- After Hibernate 5.x (5.2.x) is following JPA implementation.

| old                    | new                  |
| ---------------------- | -------------------- |
| SessionFactory         | EntityManagerFactory |
| Session                | EntityManager        |
| Transaction            | EntityTransaction    |
| save(obj):Serializable | persist(obj):void    |
| update(obj)            | merge(obj)           |
| delete(obj)            | remove(obj)          |
| get(T.class,id)        | find(T.class,id)     |

## [Spring Boot Data JPA](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Spring-Boot-Data-JPA)

###### 25th August to 9th September 2021

- Spring Boot Data JPA (Spring Data JPA) is internally using Sun JPA and JBoss Hibernate implementation.
- Data JPA Generates one Proxy class by taking SimpleJpaRepository(C) as input Template.
- Embedded Database : (No Download + No Install/ Runs at RAM) H2, Derby, and HyperSQL(HSQL).
- External Database : MySQL, Oracle, Postgress...etc
- If we move from one database to another databse only jar(Driver JAR) and properties are going to be changed. Code remains same.

**application.properties file**

```textile
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Drive
spring.datasource.url=jdbc:mysql://localhost:3306/boot9am
spring.datasource.username=test
spring.datasource.password=root
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

**application.properties file**

```textile
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/boot9am
    username: test
    password: root
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
        format_sql: true
    database-platform: org.hibernate.dialect.MySQL8Dialect

```

- For Embedded Database above properties comes with default values. Use only for Development/test purpose only, dont use at production.

- JDBC 4.x has provided -AutoLoading of Driver class
  ie if we did not specify driver class, based on URL and JAR in classpath
  Driver is loaded and Register.

- Hibernate (4.x)- Auto Detect of Dialect is exist, if we did not specify
  any dialect also fine.

**Note:**

1. Programmer has to define one interface with Model/Entity class and
   PK DataType.
1. That interface must extend any one of below type
   - CrudRepository<T,ID>
   - PagingAndSortingRepository<T,ID>
   - JpaRepository<T,ID>
1. For above interfaces implementation logic is given by : SimpleJpaRepository<T,ID>
1. At runtime by taking SimpleJpaRepository template one class is generated (no .java/.class physically exist) that gives implementation for your entity class based.
1. Transaction Management is implemented using Spring AOP (Aspected Oriented Programming) advices.
   - before advice calls beginTx() method
   - afterReturningAdvice (on success) ---> commit()
   - afterThrowingAdvice(on exception) ---> rollback()
1. Here, need not to apply @Repository annotation. No Need of Dao-DaoImpl pattern.
1. Providing driver class name and dialect in properties file is optional based on URL provided and JAR added(pom.xml) they are auto-detected/loaded.
1. Data JPA is for SQL based databases only. Not supported for NoSQL.

## [Spring Boot Data JPA : Association Mapping](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Spring%20Boot%20Data%20JPA%20Association%20Mapping)

###### 13th to 17th September 2021

### Association Mapping + Joins

#### Database :

Link one table(Primary Key) with another table(Foreign Key) using PK-FK Columns.

```textile
Multiplicity (4)
 one to one       1...1
 one to many      1...*
 many to one      *...1
 many to many     *...*

           1...1
 employee ------ passport

	  1...*
 employee ------ address

          1...*
 product ------- brand

         1...*
 vendor ------- contract

	   *...1
 employee ------ department

	  *...*
 student ------ course
```

**Hint:** \*(Many) Side FK Column is created.

**Note:**

- For one-to-many and many-to-one FK column is created at many side.
- For many-to-many one additional table is created with 2 Fk columns

```textile
       1...1
person  --- aadhar

     *...*
book ---- author

       *...*
Faculty --- Course

       1...*
Student --- Contact

       1...*
College --- Branch

--------------------------------------------------------------------------
	Non-Collection                   Collection
--------------------------------------------------------------------------
         1...1                           1...*
	 *...1                           *...*
--------------------------------------------------------------------------
```

#### Steps :

- Define two classes and apply HAS-A Relation (create HAS-A Variable)
- Check for Non-Collection | Collection Type.
  - If collection type then change HAS-A variable to collection type.
- Apply Association Mapping annotation over HAS-A Variable.

```textile
1...1 | @ManyToOne (unique condition)
*...1 | @ManyToOne
1...* | @OneToMany
*...* | @ManyToMany
```

- Provide Join Column (or) Join table at HAS-A Variable
- Draw tables with column details..

#### Cascading :-

- When we perform any non-select operation(save/update/delete) same you want to apply on child object also, then use cascading.
- If we use cascading such relation is called as Composition
  else it is called as Aggregation.

#### FetchType :-

- FetchType(enum) used for select operations, to specify should it fetch parent with child data or not.
  - **LAZY** : Fetch only parent data
  - **EAGER** : Fetch parent and its child data too.
- Default FetchType is
  - OneToMany,ManyToMany : LAZY
  - OneToOne,ManyToOne : EAGER
- Note : If child is many then default fetch type is lazy, If child is one then default fetch type is eager.

#### Joins

To fetch data from multipl table using single SELECT SQL.

- INNER JOIN | JOIN : only connected rows of both tables

- OUTERS JOIN
  - LEFT OUTER JOIN | LEFT JOIN : Left side table all rows, connected right side table rows
  - RIGHT OUTER JOIN| RIGHT JOIN : Right side table all rows, connected left side table rows
  - FULL OUTER JOIN | FULL JOIN : Both connected and non-connected rows(in simple all rows)

#### Stored procedures

- To execute set of statements as a single call can be done using
  Stored Procedure concept.
  - Stored Procedure === Java Methods
  - IN Params === Parameter
  - OUT Param === ReturnType
- At database side we create Procedure and at java side we call it/execute it.

## [Spring Boot Web Mvc](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/SpringBootWebMvc)

- MVC is a design pattern used to develop web applications.\
  M - Model (Data)\
  V - View (UI/Display)\
  C - Controller (Class/request process)\
- At least one Servlet is required to develop web applications using Java.
- Web Browser can run/understand only : HTML,CSS and Javascript.
- Servlets --> (.java)-->(.class) ->service() output : HTML => Sent to browser
- Angular --> .ts ->(bundle) .js --> .js + .css + .html -> Sent to browser

###### Controller --> for a module in a project

Ex:

```textile
 --------------------------------
  Module      ControllerName
 --------------------------------
  User        UserController
  Search      SearchController
  Cart        CartController
  Payment     PaymentController
  ..etc
```

- HandlerMapping helps FC to identify one controller based on Path + httpMethod
- Controller returns ViewName(no Location and Extension of File)
  to be independent of UI technologies.

- Controlle provides Data(Stored inside Model-->Container)
  which is optional
- Expression Language : reads data from Container at runtime
  provide to UI page
  Ex: Welcome to ${varaible}\

Ex ViewName(String) : Home, Profile, Settings (Controller returns)

ViewResolver will add prefix (location) and suffix(extension)

```textile
ViewPage ==> Prefix   +  ViewName + suffix\
  	        /mypages/      Home     .jsp
```

JSP : Sun(Oracle)\
Thymeleaf : https://www.thymeleaf.org/\
freemarker : https://freemarker.apache.org/

- Non-Java UI : Angular/ReactJS

- In Spring Boot Autoconfiguration is provided for

  1. FrontCotroller
  1. ViewResolver (provide prefix and suffix using properties)
  1. HandlerMapping

- only code for : Controller, View pages and Model(I)

- Spring Boot provides servers too (Embedded Server) No need to Download and Install
  1. Apache Tomcat (default)
  1. JBoss undertow
  1. Eclipse Jetty
