# Inputs to Application , Option Args

- Priority Order
  
  1. Option args (Commands Line Args)  (--key=val)
  2. VM Args
  3. application.properties
  4. application.yml

- What ever process we use, finally we can read data using @Value or @ConfigurationProperties only.

##### Option args (Commands Line Args)  (--key=val)

- Passsing data while running application using key=val format.

- must follow format --key=val

- This is input to main method / starter class.

- These key=val data finally stored in Common memory Environment.

##### Passing Option args using STS:

- Finish Code

- Open Main class

- Right click on Main class > Run as > Run Configuration

- Click on Arguments tab > Goto Program Arguments

- Enter data like --key=val 
  
  ```textile
  ex:
  --my.client=TEST
  ```

| Option-args          | Properties                        |
|:-------------------- | --------------------------------- |
| Client Specific Data | Database Connection               |
| Memory related thing | Email Properti                    |
| Env setup da         | ORM Properti                      |
| Profiles             | Web MVC, Security, Actuator...etc |
