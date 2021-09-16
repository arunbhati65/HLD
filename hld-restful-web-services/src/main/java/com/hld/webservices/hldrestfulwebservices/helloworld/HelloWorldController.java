package com.hld.webservices.hldrestfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping("/hello-world-bean")
	public com.hld.webservices.hldrestfulwebservices.helloworld.HelloWorldBean helloWorldBean() {
		return new com.hld.webservices.hldrestfulwebservices.helloworld.HelloWorldBean("Hello World!!");
	}

	@GetMapping("/hello-world-internationalization")
	public String helloWorldInternationalized(/*@RequestHeader(name = "Accept-Language", required = false) Locale locale*/) {
		return messageSource.getMessage("good.morning.message", null, "Default Message", LocaleContextHolder.getLocale());
		//return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
		//return "Hello World!!";
	}
}
