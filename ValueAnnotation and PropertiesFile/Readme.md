# @Value , Properties file

- Spring Boot creates and loads a file **application.properties** by default.

- Read data from properties file for our own keys using **@Value("${key}")**

- **@Value("${key}")**  expects key at proeprties file, key is not optional. If key is not present,spring boot will throw IllegalArgumentException: Could not resolve placeholder

- If any key is repeated with different values(duplicate keys) then Spring Boot override key-val, ie last combination is loaded.
  
  ```textile
  ex:
   my.app.id=10
   my.app.id=20
   my.app.id=30
   @Vaue("${my.app.id}") ---> 30
  ```

- keys are case-sensitive.
  
  ```textile
  i.e:
  my.app.ID, my.app.id are different
  ```

- symbols allowed to define a keyname in proeprties file are **. - __**

- spring boot always gives priority to application.properties file only
   even if you provide your own properties file, but if key is not present in application.properties then container will read it from your properties file.

- steps to add your own properties file
  
  ```textile
  Code Steps
   1. Right click on 'src/main/resources' folder > new > File > enter name : abcd.properties > finish
   2. open abcd.properties add your keys
   3. At main class level: //loading file into containers
   @PropertySource("classpath:abcd.properties")
  ```
