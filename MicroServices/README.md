# Microservices

### Monolithic Application

- Project is a collection of Modules. [ Application is a collection of Service]\
  ex: Amazon Application\
   Services: Search, Cart, Payment, Feedback, Trace...etc

  Gmail Application\
   Services: User (Register, Login), Inbox, Sent, Drafts..etc

  Facebook Application\
  Services: Register, Login, Post, Comment, Like, Friends , Chat ..etc

**Monolithic Application** : Creating a single deployable component (single .jar or .war) for a Project is called as "Monolithic Application"

- Finally above projects converted into Amazon.war, Gmail.war ..etc\
  compile : .java ---> .class\
  Build : .class (+HTML,CSS,Images/..etc) - .jar / .war\
  jar - java archive (stand alone application type)\
  war - web archive (web application types)

- deploy : Place build file inside server and start server.

- Service Instance / Instance :- A successful Deployment setup/Environment

- Load : Client Request (internally one Request = one threads)

- Max Load : Max no.of request allowed by server (for parallel processing)

  - Default is : 200
  - server.jetty.threads.max = 200
  - server.tomcat.threads.max = 200

- Load, MaxLoad, LoadFactor
  - Load = Current no.of Request
  - Max Load = max no.of Request allowed
  - LoadFactor = Current Load/ Max Load

```textile
     LoadFactor = Current Load/ Max Load
                = 100 / 200
                = 0.5
     0 <= LoadFactor <= 1
```

- 0 <= LoadFactor <= 1

- Scaling:- it is a process of increase the performance of system/application.

**Horizontal Scaling :** Creating multiple Service Instances and handle using LBS(Load Balancer Server) is called as Horizontal Scaling.

**Vertical Scaling :** Enhance System hardware capacity. ie RAM, CPU ,HardDisk/SSD..etc

**Load Balancer Server** :- It will dispatch request to Actual Service instance.

- It will choose an Instance which has Less Load Factor.
- If all are having same LF then choose any one Instance in Random.

- LBS will not process request.
- It will dispatch request to ServiceInstance
- Every ServiceInstance is identified using InstanceId(HexaDecimal Value)
