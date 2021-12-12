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
						.filters(f -> f.addRequestHeader("sample-request-employee-header", "Hello from request header @ Employee Service")
								.addResponseHeader("sample-response-header", "sample-response-header"))
						.uri("lb://EMPLOYEE-SERVICE") //URI
						)
				.route(
						"productServiceId", 
						r->r.path("/product/**")
						.filters(f -> f.addRequestHeader("sample-request-product-header", "Hello from request header @ Product Service")
								.addResponseHeader("sample-response-header", "sample-response-header"))
						.uri("lb://PRODUCT-SERVICE")
						)
				.build(); 
	}
}