package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder  builder){
		
		return builder.routes()
			.route(r -> r.path("/api/books/**")
				.filters(f -> f
					.addResponseHeader("X-Powered-By", "Library Gateway Service")
				)
				.uri("http://book-service:8081")
			)
			.route(r -> r.path("/api/categories/**")
				.filters(f -> f
					.addResponseHeader("X-Powered-By", "Library Gateway Service")
				)
				.uri("http://book-service:8081")
			)
			.route(r -> r.path("/api/users/**")
				.filters(f -> f
					.addResponseHeader("X-Powered-By", "Library Gateway Service")
				)
				.uri("http://user-service:8082")
			)
			.route(r -> r.path("/api/orders/**")
				.filters(f -> f
					.addResponseHeader("X-Powered-By", "Library Gateway Service")
				)
				.uri("http://order-service:8083")
			)
			.build();
	}

}
