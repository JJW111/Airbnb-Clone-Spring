package com.clone.airbnb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class SignInController {

	@GetMapping(path="admin_login")
	public String adminLogin() {
		return "admin/login";
	}
	
	
	
	@PostMapping(path="admin_session_fail")
	public String adminSessionFail() {
		return "admin/login";
	}
	
	
	
	@GetMapping(path="login")
	public String login() {
		return "user/login";
	}
	
	
	
	@PostMapping(path="session_fail")
	public String sessionFail() {
		return "user/login";
	}
	
}
