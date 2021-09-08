# Spring Boot Data JPA
#### 25,26th August 2021
- Spring Boot Data JPA (Spring Data JPA) is internally using Sun JPA and JBoss Hibernate implementation.
-  Data JPA Generates one Proxy class by taking SimpleJpaRepository(C) as input Template.
  -  Embedded Database : (No Download + No Install/ Runs at RAM) H2, Derby, and HyperSQL(HSQL).
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

### CRUDRepository
#### 27th,31st August 2021
> source : https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html

CRUDRepository(I) supports all the crud operations.



### PagingAndSortingRepository
#### 1st,2nd September 2021
> source : https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html

PagingAndSortingRepository(I) is given by Spring Data JPA.
- This is sub type of CrudRepository.
- This interface additionally provides 2 methods
  - Iterable<T> findAll(Sort sort);
  - Page<T> findAll(Pageable pageable);

### JpaRepository 
> source : https://docs.spring.io/spring-data/data-jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
- Working with JPA Concepts (Collections Mapping, Association Mapping)
- Custom Query (findBy, native SQL, JPQL/HQL)
- Example Object compare ... etc
- JpaRepository internally extends PagingAndSortingRepository (that internally extends CrudRepository)
#### Working with Collections using JPA
#### 3rd,4th September 2021

```textile
JPA Supports : List, Set and Map collections mapping with Database table.

	 --------------------------------------------
  	    Non-Index based            Index Based
	 --------------------------------------------
	          Set                    List, Map
	 --------------------------------------------
```
- If we define entity class with primitive variables
   then ORM maps with one table(parent table) and variables as columns.
- if we use collection variable, then ORM maps with one child table.
```textile
 1 Collection variable = 1 Child table.
```
- Such child table is created with 3 columns. They are:
   1. Join Column (FK) : Which links parent and child tables.
   1. Element/Data Column : Actual data of collection
   1. Index column : it is applied only for List and Map type. List Index number starts from zero.Map Key behaves like index.
- Set collection comes under non-index type. So this table is created
	only with 2 columns.

```java
@ElementCollection : Must be applied over every collection variable
@CollectionTable(
    name="",                              // child table name
    joinColumns= @JoinColumn(name="")     // FK Column name
)
@Column(name="")       //element column name
@MapkeyColumn(name="") // index column for Map
@OrderColumn(name="")  //index column for List
```

#### Working with Date and Time 
#### 6th September 2021

Hibernate with JPA, has provided one annotation:\
@Temporal with one Enum TemporalType(E) => DATE, TIME and TIMESTAMP


- we must use java.util.Date(C) that can be mapped to internally
  - java.sql.Date (DATE), 
  - java.sql.Time (TIME) ,
  - java.sql.Timestamp (TIMESTAMP)

##### Note:
1. Date(java.util) is given by JavaSE, which is used to represent date and time using TimeZone, Month name, Day name count...etc

1.  But we want to store date in Database(java.sql), then 3 concept are used which internally uses java.util.Date only
    1. Store only Date : year-month-day (java.sql.Date)
    1. Store only time : hrs:mins:sec (java.sql.Time)
    1. Store Both Date and time : year-month-day hrs:mins:sec (java.sql.Timestamp)
1. If we did not specify type @Temporal and TemporalType then default is given as: @Temporal(TemporalType.TIMESTAMP)

 SimpleDateFormat(C) : this is used to format Date object to dislay at browser/to UI
> source : https://docs.oracle.com/javase/10/docs/api/java/text/SimpleDateFormat.html

#### Working with BLOB and CLOB
#### 6th September 2021

BLOB and CLOB are Datatypes in Oracle DB.


Q) How can modify/ set max size of LOBs in Database?\
A) set max_allowed_packet 
>source : https://stackoverflow.com/questions/8062496/how-to-change-max-allowed-packet-size

- @Lob annotation is given by javax.persistence package.
- if we use byte[] + @Lob then it creates BLOB
  and char[] + @Lob creates CLOB types in database
- BLOB supports : PDF, Excel, Word, Video, Audio, Image...etc
- CLOB Supports : text data only

#### Working with Custom Query
#### 7th,8th September 2021

Custom Query : 
- Define your own Query(SQL, JPQL/HQL)
- We can write in 2 ways :
    1. findBy [Abstract method] : SELECT Operations only
    1. @Query("")               : Both SELECT and NON-SELECT, Joins, manual SQL, JPQL/HQL.
##### findBy [Abstract method]
- We have to define one abstract method inside Repository interface.
- That generates one SELECT SQL at runtime.
- Need not to define any query manually.
- It supports Restections(WHERE clause) and Projections(SELECT clause)
- Must specify variables conditions returns and params  to generate SQL query (dialect)
- Simple meaning of findBy : select * from (table) WHERE (conditions);
- Need not to define any SQL. Here is to compare with findBy.
##### Syntax
``` textile
    <Return> findBy<VariablesAndConditions>(<Params>)
```
- uses class and variables names. Not used table and column names.
- Parameter DataType need to be matching. But Parameter variable name can be any name.
- Possible Types\
List<T>		:	Result of Query is multiple Rows\
Optional<T>	:	Result of Query is one row\
In the place of Optional we may use List, but in place of List if we use optional, then NonUniqueResultException: query did not return a unique result
#### Examples
```java
class Employee {
  private Integer empId;
  private String empName;
  private Double empSal;
}
```
```sql
#1  List<Employee>  findByEmpName(String empName);
//Generated SQL:
    SELECT * FROM EMPTAB WHERE ENAME=?

#2  List<Employee>  findByEmpSal(Double empSal)
//Generated SQL:
    SELECT * FROM EMPTAB WHERE ESAL=?

#3 List<Employee> findByEmpSalLessThan(Double empSal)
//Generated SQL:
    SELECT * FROM EMPTAB WHERE ESAL<?

#4 List<Employee> findByEmpNameIsNotNull();
//Generated SQL:
    SELECT * FROM EMPTAB WHERE ENAME is not null
```