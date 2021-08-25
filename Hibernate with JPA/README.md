# ORM 

#### ORM :-

- Object Relational Mapping , every DB Operation using class-objects.\
  Ex: save(obj), update(obj), delete(obj), get(id):obj
- Mapping Rule followed by programmer\
  a. Class ------Mapped with------- Table\
  b. Variable ---Mapped with------- Column

##### ORM properties :-

a. dialect : dialects are classes which generates SQL query when we perform operation

source : https://docs.jboss.org/hibernate/orm/5.4/javadocs/org/hibernate/dialect/packagesummary.html

Package name : org.hibernate.dialect\
OracleDialect\
Oracle10gDialect\
Oracle9iDialect\
Oracle12cDialect\
MySQL8Dialect

b. show_sql : [boolean]
true --> Display Generated SQLs at Console\
(default) false --> Do not Display any SQL query.

c. format_sql: [boolean]
true ---> Print SQL at console clause by clause

```sql
 SAMPLE OUTPUT:
 SELECT
      eid,ename
 FROM
      emptab
 WHERE
      esal>200;
```

d. hbm2ddl.auto : Schema Generation\
 Having 4 possible values

1.  validate :[default] Programmer takes care of creating/drop/alter tables.
1.  create : Hibernate creates table new table alwasy (if old table exist drop it and create new one)
1.  update : Hibernate creates table new table if not exist else use old table.
1.  create-drop : Hibernate creates table new table always (if old table exist drop it and create new one). once operation is done, again drop table (when we stop server/app stopped/finished).
    - Realtime (Production): validate / update\*\*
    - Dev Environment : create
    - Testing purpose only : create-drop

##### API :-

Collection of classes/intefaces/enum/annotation..etc.Used for a Purpose.\
ex:\
Java Mail API : Used for EMail Apps\
JDBC API : Used for Database Operation\
Servlets API :Used for Dynamic Web Apps\
JMS API : used for Message Queues\
JAXB API : Used for Marshalling/Unmarshalling (XML-JAVA) ..etc\

1st Party : API Specification\
2nd Party : API usage for Application\
3rd Party : API Implementation\
**1st Party Provides API, 2nd Party uses API for Projects where 3rd Party given implementation to few interfaces, logic for annotation processing..etc**

# Hibernate with JPA

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

**Note :**
1.  JPA is a Specification given by Sun/Oracle. That follows ORM Concept.
1.  It has provided interfaces like EntityManagerFactory, EntityManager and EntityTransaction..etc
1.  Hibernate is a Implementation of JPA (After Hiberenate 5.2.x) SourceForge developed Hibernate-Taken by JBoss and implemented using JPA.
1.  Programmer has to follows JPA Spec, so that in future if vendor is changed our code remains same, bit config/jars may be changed.
1.  Persistence(C) is using your XML Configuration and loading driver, creating connection, supporting pooling, cache..etc returns Reference as EntityManagerFactory
1.  Database Operations: INSERT, UPDATE, DELETE and SELECT are done using EntityManager(I) created using EntityManagerFactory(I).
1. EntityTransaction: Set of Operations executed between beginTx--- commit/rollback is applied as single Tx.
```textile
 Begin
 Operation#1 (success) 1-row insert
 Operation#2 (success) 1-row insert
 Operation#3 (success) 1-row insert
 Operation#4 (fail)
 Commit/Rollback

If atleast one failed, all are failed.
```
- By default every column accepts null values, if we dont want to allow
use nllable=false (default is true)
- If type is String(varchar)--default size is 255 chars
to reduce this use length =___
Ex:
@Column(name="sname",nullable = false,length = 25)