# Spring Boot Data JPA - Association Mapping

## Association Mapping + Joins

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
- For one-to-one , we choose \*...1 and many side UNIQUE condition is applied.
- For any Association Mapping, code must be given at HAS-A variable only\
  ie JoinColumn, JoinTable, OneToMany/ManyToOne/ManyToMany...etc

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
1...1 | @OneToOne
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

### @OneToOne

- Hibernate old version (3.x) [not following JPA]\
   1...1 = \*...1 (@ManyToOne + Many side unique condition)

- Hibernate with JPA (new versions)\
   1...1 = @OneToOne annotation

### Joins

To fetch data from multipl table using single SELECT SQL.

- INNER JOIN | JOIN : only connected rows of both tables

- OUTERS JOIN
  - LEFT OUTER JOIN | LEFT JOIN : Left side table all rows, connected right side table rows
  - RIGHT OUTER JOIN| RIGHT JOIN : Right side table all rows, connected left side table rows
  - FULL OUTER JOIN | FULL JOIN : Both connected and non-connected rows(in simple all rows)

#### Syntax (HQL Joins)

```sql
SELECT P.<variable>, C.<variable>

FROM ParentClass P
   ( [join type ]
  p.<hasAVariable> as C )  [repeate for multiple classes]

WHERE <Condition>;

```

> SQL joins:- https://www.w3schools.com/sql/sql_join.asp

```java
Employee---<>Address

class Address{
  //aid,loc,pin
}
class Employee{
  //eid,ename,esal
  Address addr;
}
```

#### HQL Joins Syntax:

```sql
SELECT e.ename, a.loc

FROM  Employee e
     INNER JOIN
     e.addr as a

WHERE e.eid=?
```

- To implement Joins Two model/entity classes must have been connected
  using HAS-A Relation and any one Association Mapping(1..1/1...\* ...etc)
- We can fetch data from tables, no INSERT/UPDATE/DELETE can be done using joins
- Any one Join type must be used.

```textile
INNER JOIN  (or)  JOIN
LEFT OUTER JOIN  (or) LEFT JOIN
RIGHT OUTER JOIN  (or) RIGHT JOIN
FULL OUTER JOIN  (or) FULL JOIN  (This may not be suported by all DBs)
```

- words INNER AND OUTER are optional to use.

```sql
select e.ename, p.pname
from emptab e
full outer join
projtab p
on e.pid_fk=p.pid;
```

### Stored procedures

- To execute set of statements as a single call can be done using
  Stored Procedure concept.
  - Stored Procedure === Java Methods
  - IN Params === Parameter
  - OUT Param === ReturnType
- At database side we create Procedure and at java side we call it/execute it.

```textile
JDBC:
 CallableStatement
JPA/Hibernate:
 @NamedQuery
 StoredProcedureQuery(I)
Data JPA:
 @Procedure,@Query(value ="{call GET_ALL_EMPS ()}",nativeQuery = true)

Ex:
Fetch Data from two tables , calculate some data, write result to another table.
(like java method)
> Define multiple SQLs
> calculations/operation
```
#### MySQL Syntax to create Stored Procedure---
https://dev.mysql.com/doc/connector-net/en/connector-net-tutorials-stored-procedures.html
```sql
DELIMITER $$
CREATE PROCEDURE <name>
	BEGIN
        statements;
	END$$
DELIMITER ;
```
#### Examples
##### Case 1: Stored Procedure (No IN PARAM, No OUT Param)\
Default OUT: SELECT QUERY RESULT
```sql
DELIMITER $$
CREATE PROCEDURE GET_ALL_EMPS()
	BEGIN
		SELECT * FROM EMPTAB;
	END$$
DELIMITER ;
```
##### Case 2 Stored Procedure (with IN PARAM, No OUT Param)\
Default OUT: SELECT QUERY RESULT
```sql
DELIMITER $$
CREATE PROCEDURE GET_EMPS_BY_DESG(IN EMP_DESG VARCHAR(20))
	BEGIN
		SELECT * FROM EMPTAB WHERE EDESG=EMP_DESG;
	END$$
DELIMITER ;
```
##### Case 3 Stored Procedure (with IN PARAM, with OUT Param)
```sql
DELIMITER $$
CREATE PROCEDURE GET_EMPS_COUNT_DEPT(IN EMP_DEPT VARCHAR(20), OUT dept_count INT)
	BEGIN
		SELECT COUNT(*) INTO dept_count FROM EMPTAB WHERE EDEPT=EMP_DEPT;
	END$$
DELIMITER ;
```
