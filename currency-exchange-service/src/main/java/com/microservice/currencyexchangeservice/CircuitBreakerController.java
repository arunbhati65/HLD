package com.microservice.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private ResponseEntity<String> forEntity;
	private Logger fLogger= LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/sample-api")
	//@Retry(name = "sample-apii",fallbackMethod = "hardcodedResponse")// 3 times by default
	//@CircuitBreaker(name = "default",fallbackMethod = "hardcodedResponse")//closed->open->half_open->closed
	//@RateLimiter(name="default")
	@Bulkhead(name="default")//max concurrent calls allowed
	public String sampleApi(){                                            //              half_open ->open
		fLogger.info("Sample API Call Received");
		forEntity=new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",String.class);
		return forEntity.getBody();
	}

	public String hardcodedResponse(Exception ex){
		return "fallback-response ->"+ ex.getMessage();
	}
}
