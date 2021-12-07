**Route :** Navigating to a Microservice when a request comes to Gateway.

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