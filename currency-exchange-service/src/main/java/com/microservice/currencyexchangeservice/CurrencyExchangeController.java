package com.microservice.currencyexchangeservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	Logger fLogger= LoggerFactory.getLogger(CurrencyExchangeController.class);
	@Autowired
	private Environment fEnvironment;

	@Autowired
	private CurrencyExchangeRepository fCurrencyExchangeRepository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchangeValue(
			@PathVariable String from,
			@PathVariable String to
	){
		fLogger.info("retriveExchangeValue called with {from} to {to}",from,to);
		CurrencyExchange currencyExcahnge =fCurrencyExchangeRepository.findByFromAndTo(from,to);
				//new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(75));
		if(currencyExcahnge==null)
			throw new RuntimeException("unable to find data"+from+"---"+to);
		String port=fEnvironment.getProperty("local.server.port");
		currencyExcahnge.setEnvironment(port);

		return currencyExcahnge;
	}
}
