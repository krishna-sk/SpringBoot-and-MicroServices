# Spring Boot Profiles Using Inputs (Properties/YAML)

## Run Application in different profiles

### Naming Convention for properties files based on profiles

```textile
application.properties - > default profile
syntax:
    application-{profile-name}.properties -> profileName
ex:
    application-dev.properties
```

### Naming Convention for yml files based on profiles

```textile
Single yml file for all the profiles
profiles are sepatated by --- and specified by
syntax:
spring:
  config:
    activate:
      on-profile:
        - profileName
ex:
spring:
  config:
    activate:
      on-profile:
        - dev
```

**To activate the profiles in the spring boot we will be using command line arguments**
**we should specify the below command in the in the run configutation arguments**
**Based on the requrired profile like dev, prod, qa**

```textile
for dev  :: --spring.profiles.active=dev
for prod :: --spring.profiles.active=prod
for qa   :: --spring.profiles.active=qa
```

- **first priority is given to application.properties and rather the application.yml**
- **If same keys are present in both application.properties and application.yml, keys present in the properties file will be loaded.**
- **if any keys are not present in the properties file then those keys are loaded from yml file if present. if keys are not present in both files then exception is thrown**
