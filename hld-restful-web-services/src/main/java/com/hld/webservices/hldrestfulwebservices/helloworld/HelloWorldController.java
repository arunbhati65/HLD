package com.hld.webservices.hldrestfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/hello-world")
	public String helloWorld(){
		return "Hello World";
	}

	@GetMapping("/hello-world-bean")
	public com.hld.webservices.hldrestfulwebservices.helloworld.HelloWorldBean helloWorldBean(){
		return new com.hld.webservices.hldrestfulwebservices.helloworld.HelloWorldBean("Hello World!!");
	}
}
