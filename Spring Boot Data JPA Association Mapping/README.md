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
**Hint:** *(Many) Side FK Column is created.

**Note:**
- For one-to-many and many-to-one FK column is created at many side.
- For many-to-many one additional table is created with 2 Fk columns
- For one-to-one , we choose *...1 and many side UNIQUE condition is applied.
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
- Check for Non-Collection  | Collection  Type.  
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