package com.microservice.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private ResponseEntity<String> forEntity;
	private Logger fLogger= LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/sample-api")
	@Retry(name = "sample-apii",fallbackMethod = "hardcodedResponse")// 3 times by default
	public String sampleApi(){
		fLogger.info("Sample API Call Received");
		forEntity=new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",String.class);
		return forEntity.getBody();
	}

	public String hardcodedResponse(Exception ex){
		return "fallback-response ->"+ ex.getMessage();
	}
}
