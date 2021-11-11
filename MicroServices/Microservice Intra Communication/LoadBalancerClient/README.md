## Execution Order

1. Run Eureka Server
2. Run Vendor service ( 3 times -- use different port number)
3. Run Product Service ( 1 time)
4. Go to Eureka : http://localhost:8761/
5. click on product link\
   Ex: http://nit:8686/actuator/info
   http://localhost:8686/actuator/info
6. Modify URL as:\
   Ex: http://nit:8686/product/info
   http://localhost:8686/product/info
