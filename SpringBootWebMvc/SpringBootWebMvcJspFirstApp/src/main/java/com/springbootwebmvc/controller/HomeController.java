package com.springbootwebmvc.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Object + Http Protocal\
/*
 * Every Component (class annotated with any stereotype) class object is created
 * while starting application only. To make this object creation on first
 * request/ first method call/first access use annotation @Lazy(+@Component)
 * over class or @Bean + @Lazy
 */
@Lazy
public class HomeController {

//	@RequestMapping(value="/show", method=RequestMethod.GET)
	@RequestMapping(value = "/show")
	public String showPage() {
		return "HomePage";
	}
}
