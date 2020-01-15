package com.clone.airbnb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.messages.Messages;
import com.clone.airbnb.messages.Tags;
import com.clone.airbnb.service.UserService;



@Controller
public class SignInController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(path="/login")
	public String login() {
		return "user/login";
	}
	
	
	@GetMapping(path="/admin_login")
	public String adminLogin() {
		return "admin/login";
	}
	
	
	
	@PostMapping(path="/admin_session_fail")
	public String adminSessionFail() {
		return "admin/login";
	}
	
	
	
	@PostMapping(path="/session_fail")
	public String sessionFail() {
		return "user/login";
	}
	
	
	@GetMapping(path="/session_success")
	public String sessionSuccess(RedirectAttributes redirectAttr) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		String username = (String) principal;
		
		SafeUser user = userService.profile(username);
		redirectAttr.addFlashAttribute("messages", Messages.builder()
				.add("Welcome back " + user.getFirstName(), Tags.SUCCESS)
				.build());
		return "redirect:/";
	}
	
	
	@GetMapping(path="/session_out")
	public String logout(RedirectAttributes redirectAttr) {
		redirectAttr.addFlashAttribute("messages", Messages.builder()
				.add("See you laster", Tags.INFO)
				.build());
		SecurityContextHolder.clearContext();
		return "redirect:/";
	}
	
}
