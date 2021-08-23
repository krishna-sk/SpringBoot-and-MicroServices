# Project Lombok API

- This is Open Souce java API, used to generate source code before compile

```textile
Example:
you can generate
default constructor
parameterized constructor
set and get methods
toString() method
equals() and hashCode() methods
```

- maven dependency 
  
  ```textile
  <dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  </dependency>
  ```
- Version is not required to specify this is taken care by Spring Boot Parent Project

### Annotations

We can apply at class level so that generated for every variable. 
We can apply at selected vriable level, to avoid for all variables.

- **@NoArgsConstructor** : This annotation is used to generate Default/zero param constrcutor.

- **@AllArgsConstructor** : It will generate all variables as Params constrcutor.
  (if class has zero variables then generates Default/zero param constrcutor)

- **@Setter / @Getter** : To generate set/get methods we can use these
  annotations

- **@ToString** : This annotation is used to override toString() method in your class
  
  toString() : This method exist in Object class (Which is common super class for
  all).We can override this method : non-final, non-static and non-private.Default logic is **Full Class name + @ + HashCode-In Hexa Decimal value**

- **@RequiredArgsConstructor** : This annotation is used to generate selected variables constrcutor. To select variable apply @NonNull over variable. @RequiredArgsConstructor will generate default constrcutor if no variable is having @NonNull annotation.

- **@EqualsAndHashCode** : To compare two different object data (not references) 
    we have to override equals() method in our class.
  
  equals() and ==  'by default they are same, until we override equals method in our class.equals() method is defined in Object class which is using == operator
  inside equals() method

- **@Data** : This is simplified annotation that provides
  
  - Setters & Getters
  
  - ToString
  
  - EqualsAndHashCode
  
  - RequiredArgsConstructor

- @Data provides Required Args Constrcutor only if no external annotation is applied Constructor Annotation (@NoArgsConstructor, @AllArgsConstructor)
  
  
