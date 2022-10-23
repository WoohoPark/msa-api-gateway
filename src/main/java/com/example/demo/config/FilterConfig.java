package com.example.demo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {

	@Bean
	public RouteLocator routers(RouteLocatorBuilder builder){
		return builder.routes()
				.route(r -> r.path("/user/**")
						.filters(f -> f.addRequestHeader("user-request","user-request-header")
								.addResponseHeader("user-response","user-response-header"))
						.uri("http://localhost:8890"))
				.route(r -> r.path("/board/**")
						.filters(f -> f.addRequestHeader("board-request","board-request-header")
								.addResponseHeader("board-response","board-response-header"))
						.uri("http://localhost:8891"))
				.build();
	}
}
