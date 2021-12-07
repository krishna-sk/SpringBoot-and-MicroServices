## API Gateway

### Spring Cloud API Gateway

- There are multiple components in Microservice Design and all running at different locations (IPs/Ports).

- There must be one common and entry and exit point for our Microservice setup. ie Gateway (Spring Cloud Gateway)

- Gateway is used for

  1.  Routing : Executing a Microservice when request made by client.

      - Static Routing : (single Microservice instance), Gateway makes a direct call to Microservice
      - Dynamic Routing: (Multiple Microservice instances), gateway goes to eureka to fetch A ServiceInstance having less load factor.

  1.  Filters : Modify Request/Response Sections, Adding Header, Req Params..etc

  1.  Security :- We can implement Token based Security (JWT/Oauth/3rd party)
      (ReST is stateless, No HttpSession)

- For a Reuqest it will pass API Gateway once, if there is Microservice Intracommunication then no need to go through API gateway again.
- Gatway makes request to Microservice, Eureka never makes any request to Microservice.
- Even Gateway App must be register with Eureka, like other Microservice apps.
- API Gateway also one type of Microservice, that can make call to any Microservice in App, using eureka it gets serviceInstance data.
- Gateway Application contains HandlerService that chooses one ServiceId[Dynamic Routing]/URL[static rounting] of Microservice based on Path Condition(Predicate).
- There are optional Filters used. Pre-Filter executed before sending request to Microservice and Post-Filter is executed after getting response from Microservice.\
  Pre-Filter --> works on Request\
  Post-Filter--> Works on Response

### Routing Configuration using Spring Cloud Java Config

**route :** Navigating to a Microservice when a request comes to Gateway.

- A Route must have Predicate(Condition, Path), URI(static/lb), Filters(optional) all are identified using one unique ID.
- 1 RouteDefinition = 1 Id ( Predicate + URI + Fitlers ) [For one Microservice app]
- Java Configuration is the mostly used configuration in API Gateway.
- RouteLocator (List<Routes>) => Builder class => Route Obj (Id,Path,URI, Filters).

```java
@Configuration
public class SpringCloudGatewayConfig {
	@Bean
	public RouteLocator configRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(
						"employeeServiceId", //ID
						r->r.path("/employee/**") //Predicate(Path)
						.uri("lb://EMPLOYEE-SERVICE") //URI
						)
				.route(
						"productServiceId",
						r->r.path("/product/**")
						.uri("lb://PRODUCT-SERVICE")
						)
				.build()
				;
	}
}
```

```java
RouteLocatorBuilder(C) {
 Builder routes() {}
}

class Builder {
  List<Buildable<Route>> routes;
}

public interface Buildable<T> {
	T build();
}

class Route {
  id, Predicate, URI, filters
}
```

##### For static routing

- Do not use lb://Service-id in case of Single Instance.Provide direct URL of Microservice (http://IP:PORT).
- Request Path must be matching with Controller path
- Here /\*\* indicates any additional path with base.

```java
package in.nareshit.raghu.config;
@Configuration
public class SpringCloudGatewayConfig {
	@Bean
	public RouteLocator configRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(
						"employeeServiceId", //ID
						r->r.path("/employee/**") //Predicate(Path)
						.uri("http://localhost:9900") //URI
						)
				.route(
						"productServiceId", //ID
						r->r.path("/product/**") //Predicate(Path)
						.uri("http://localhost:8080") //URI
						)
				.build();
	}
}
```
