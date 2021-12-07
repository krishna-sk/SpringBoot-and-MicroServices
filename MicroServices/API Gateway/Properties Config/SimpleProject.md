#### Execution order

1. Run Eureka Server
1. Run Microservice Applications
1. Run ApiGateway

###### Note:

- For Dynamic Routing

  - I. Add Instance-id at Microservice application and run multiple times
  - II. At Gateway App use lb://SERVICE-ID insted of URL of Microservice

- By default Gatway adds Loadbalancer Client and generates code as Proxy.
