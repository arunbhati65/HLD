package com.microservice.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	@Autowired
	private  CurrencyExchangeProxy fCurrencyExchangeProxy;
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionUsingFeign(@PathVariable String from,
	                                                      @PathVariable String to,
	                                                      @PathVariable BigDecimal quantity){
		CurrencyConversion currencyConversion=fCurrencyExchangeProxy.retriveExchangeValue(from,to);
		currencyConversion.setQuantity(quantity);
		BigDecimal amountTotal=quantity.multiply(currencyConversion.getConversionMultiple());
		currencyConversion.setTotalCalculatedAmount(amountTotal);
		return currencyConversion;
	}

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
	                                                      @PathVariable String to,
	                                                      @PathVariable BigDecimal quantity){
		HashMap<String,String> uriVariables=new HashMap<>();
		uriVariables.put("from",from);
		uriVariables.put("to",to);
		CurrencyConversion currencyConversion=new RestTemplate()
				.getForEntity("http://localhost:8001/currency-exchange/from/{from}/to/{to}"
						,CurrencyConversion.class,uriVariables).getBody();
		currencyConversion.setQuantity(quantity);
		BigDecimal amountTotal=quantity.multiply(currencyConversion.getConversionMultiple());
		currencyConversion.setTotalCalculatedAmount(amountTotal);
		return currencyConversion;
	}
}
