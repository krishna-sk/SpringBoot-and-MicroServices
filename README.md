# SpringBoot-and-MicroServices

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

###### 26th, 27th, 28th July 20211

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

## [Spring Boot Profiles Using Inputs (Properties/YAML)](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Spring%20Boot%20Profiles%20Using%20Inputs%20(Properties%20and%20YAML))

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

## [Spring Boot Profiles Using Coding (Logic) -- @Profile](https://github.com/krishna-sk/SpringBoot-and-MicroServices/tree/master/Spring%20Boot%20Profiles%20Using%20Profile%20Annotation)

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