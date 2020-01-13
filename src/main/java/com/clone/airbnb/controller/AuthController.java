package com.clone.airbnb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/auth")
public class AuthController {

	@GetMapping(path="")
	public String auth() {
		return "auth/index";
	}
	
	
	
	@GetMapping(path="/a")
	public String authA() {
		return "auth/index";
	}
	
}
