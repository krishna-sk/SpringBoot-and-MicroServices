# CommandLineRunner & ApplicationRunner

- Runner :  It is a block of code that gets executed only once when application is started. 
  
  ```textile
  Ex:
  Test Our Logic, CRUD Operation Test
  ```
  
  ### Types of Runners
  
  - CommandLineRunner (Interface)
  
  - ApplicationRunner (Interface)

- Difference between CommandLineRunner & ApplicationRunner : Working process is same. Only difference comes at Inputs.

- CommandLineRunner reads inputs from main() method and stores as Array format

- Environment(I) (env) : This object is created by Spring Container that holds all key-val loaded from different inputs

- StandardEnvironment(C) Impl class choose by Spring Container for Environment(I)

- ApplicaionRunner reads inputs from main() method and convers into Option and Non-Option args, given to ApplicationRunner

- Option Args : Keys and values

```html
--id=123 --name=krishna --dept=statistics
```

- Non-Option Args

```textile
user student employee 
```

- If we define multiple Runners in Spring Boot application they are executed in Unicode naming order.(A-Z order)

- Un Ordered Runners are executed at last with naming rule and with last priority.

- Runner can
  
  1. Read data from Environment( Option-Args, VM args, Properties, YAML)
  
  2. Can read objects from container (Autowired)
  
  3. Support Setup Data exeuectionn modify runners to our own order using annotation @Order(int value)

- If we did not specify any Order or just annotatin @Order without value then default value is: 2147483647 (Integer.MAX_VALUE)

- If Runners are having same order then again apply naming rule

- we can pass Input(key-vals) to Spring container in 4 ways  
  
  1. Option-args (Command Line Args --key=val)
  2. VM/System (JVM Arguments)
  3. application.properties
  4. application.yml

- we can read data from Spring Container to our code using
  
  1. @Value  (in case of Annotation Configuration)
  2. Environment Autowired , env.getProperty() (in case of Java Configuration)
  3. @ConfigurationProperties (For Spring Boot)
