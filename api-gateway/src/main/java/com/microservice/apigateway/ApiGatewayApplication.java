package com.microservice.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	//effective way to route
	//security
	//monitoring metrics
	//match routes on any request attribute
	//define predicates and filters
	//load balancing(Soring cloud discovery client)
	//path rewriting
}
