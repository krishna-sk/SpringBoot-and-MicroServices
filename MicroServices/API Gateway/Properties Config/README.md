#### Execution order

1. Run Eureka Server
1. Run Microservice Applications
1. Run ApiGateway

###### Note:

- For Dynamic Routing

  - I. Add Instance-id at Microservice application and run multiple times
  - II. At Gateway App use lb://SERVICE-ID insted of URL of Microservice

- By default Gatway adds Loadbalancer Client and generates code as Proxy.

```yml
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka

server:
  port: 8090

spring:
  application:
    name: GATEWAY
  cloud:
    gateway:
      routes:
      - id: employeeModule
        predicates:
        - Path=/employee/**
        uri: lb://EMPLOYEE-SERVICE  
      - id: productModule
        predicates:
        - Path=/product/**
        uri: lb://PRODUCT-SERVICE      


```