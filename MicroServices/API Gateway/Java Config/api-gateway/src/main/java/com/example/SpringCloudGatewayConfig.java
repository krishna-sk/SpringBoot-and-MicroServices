package com.example;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
				.build(); 
	}
}