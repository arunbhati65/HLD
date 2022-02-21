package com.microservice.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment fEnvironment;
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchangeValue(
			@PathVariable String from,
			@PathVariable String to
	){
		CurrencyExchange currencyExcahnge = new CurrencyExchange(1000L, from, to,
				BigDecimal.valueOf(75));
		String port=fEnvironment.getProperty("local.server.port");
		currencyExcahnge.setEnvironment(port);
		return currencyExcahnge;
	}
}
