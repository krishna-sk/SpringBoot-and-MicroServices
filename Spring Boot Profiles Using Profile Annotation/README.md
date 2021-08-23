# Spring Boot Profiles Using Coding (Logic) -- @Profile

- To execute class/code based on environment then use **@Profile** on top of class
- A class has no  @Profile annotation means execute logic in any\
 Ex:
  1. SwaggerUi (RestWebservices Test Screen) -- @Profile({"default","qa"})
  1. RoleDataInsert (QA) -- @Profile("qa")
  1. GoogleMailService -- @Profile("prod")

- @Profile can be placed at any class.
- If current profile is matched with @Profile then code will be executed.
- We can provide multiple profile names by using @Profile({"profile-1","profile-2"})
- A class which has no @Profile annotation is executed in every environment.
### If No active profile is set, then default profile is active.
- Here even though if we didn't pass **--spring.profiles.active=default** both classes A and B will be executed.
```java
@Profile("default")
 class A(){
     
 }
 
 class B(){
    
 }
```
- A class having @Profile("default") will be executed only in default environment(Developer Machine)
- If we **didn't** pass any profile active key like **--spring.profiles.active=profileName** then 2 types of classes are executed.
  1. Classes not having @Profile Annotation
  1. Classes has @Profile("default") annotation
- If we pass **--spring.profiles.active=default** then 2 types of classes are executed.
  1. Classes not having @Profile Annotation
  1. Classes has @Profile("default") annotation
 - If we pass **--spring.profiles.active=prod** then 2 types of classes are executed.
   1. Classes not having @Profile Annotation
   1. Classes has @Profile("prod") annotation
 - If we pass **--spring.profiles.active=qa** then 2 types of classes are executed.
   1. Classes has @Profile("qa") annotation
   1. Classes not having @Profile Annotation
 - We can even execute a logic using not profile case also.\
 Ex: Do not execute logic in qa profile
   - @Profile("!qa ")
  
 #### @Profile Scope
 - **NO @Profile Annotation**  --> Runs in every Every Environment
   - No Arguments need to be passed
 - **@Profile("default")**  --> Runs in default Environment only
   - **--spring.profiles.active=default** Argument need to be passed
 - **@Profile("prod")**  --> Runs in prod Environment only
   - **--spring.profiles.active=prod** Argument need to be passed
 - **@Profile("qa")**  --> Runs in qa Environment only
   - **--spring.profiles.active=qa** Argument need to be passed
   
   
   
 ### Input files(properties/yaml) working process and @Profile annotation working process are different
 - Based on profile only keys= values are loaded from properties/yaml files.
 - If **no profile** is specified or **default** profile is specified then priority is given to 
    1. application.properties &nbsp;&nbsp;(If not found then next)
    1. application.yml &nbsp;&nbsp;(If not found then Exception)
 - If **default** profile is specified (--spring.profiles.active=default) then priority is given to 
    1. application.properties &nbsp;&nbsp;(If not found then next)
    1. application.yml &nbsp;&nbsp;(If not found then Exception)
 - If **prod** profile is specified(--spring.profiles.active=prod) then priority is given to 
    1. application-prod.properties &nbsp;&nbsp;(If not found then next)
    1. application-prod.yml &nbsp;&nbsp;(If not found then next)
    1. application.properties &nbsp;&nbsp;(If not found then next)
    1. application.yml &nbsp;&nbsp;(If not found then Exception)
 - If **qa** profile is specified(--spring.profiles.active=qa) is given then priority is given to 
    1. application-qa.properties &nbsp;&nbsp;(If not found then next)
    1. application-qa.yml &nbsp;&nbsp;(If not found then next)
    1. application.properties &nbsp;&nbsp;(If not found then next)
    1. application.yml &nbsp;&nbsp;(If not found then Exception)
 - If **uat,prod** profile is specified(--spring.profiles.active=qa) is given then priority is given to 
    1. application-pord.properties &nbsp;&nbsp;(If not found then next)
    1. application-uat.properties &nbsp;&nbsp;(If not found then next)
    1. application-prod.yml &nbsp;&nbsp;(If not found then next)
    1. application-uat.yml &nbsp;&nbsp;(If not found then next)
    1. application.properties &nbsp;&nbsp;(If not found then next)
    1. application.yml &nbsp;&nbsp;(If not found then Exception)
## --spirng.profiles.active=a,b,c,d
   - a.properties
      ```textile
      my.id=1
      my.name=a-properties
      ```
   - a.yml
      ```textile
      my:
        id: 2
        name: a.yml
      ```
   - b.properties
      ```textile
      my.id=3
      my.name=b-properties
      ```
   - b.yml
      ```textile
      my:
        id: 4
        name: a.yml
      ```
   - c.properties
      ```textile
      my.id=5
      my.name=c-properties
      ```
   - c.yml
      ```textile
      my:
        id: 6
        name: c.yml
      ```
   - d.properties
      ```textile
      my.id=7
      my.name=d-properties
      ```
   - d.yml
      ```textile
      my:
        id: 8
        name: d.yml
      ```
    
  **Here a.yml is loaded first and then a.properties, b.yml, b.properties, c.yml, c.properties, d.yml, d.properties.So the values which are loaded from a.yml will be overridden by a.properties and this list goes on in the same order specified in the --spring.profiles.active command**
  #### **Output**
  app.id=8\
  app.name=d-properties
  
 
