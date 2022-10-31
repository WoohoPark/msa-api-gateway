package com.example.demo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ApigatewayserviceApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ApigatewayserviceApplication.class)
				.web(WebApplicationType.REACTIVE) // .REACTIVE, .SERVLET
				.run(args);
	}
}
