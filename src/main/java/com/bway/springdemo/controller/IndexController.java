package com.bway.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //request processing
public class IndexController {

	@RequestMapping(value ="/", method = RequestMethod.GET)
	public String showIndex() {
		return "LoginForm";
	}
	
}

