package com.clone.airbnb.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.messages.RedirectMessageSystem;
import com.clone.airbnb.messages.Tags;
import com.clone.airbnb.security.AuthenticationSystem;
import com.clone.airbnb.service.UserService;



@Controller
public class SignInController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(path="/login")
	public String login(RedirectAttributes redirectAttr) {
		if (!AuthenticationSystem.loggedOutOnly(redirectAttr)) {
			return "redirect:/";
		}
		
		return "user/login";
	}
	
	
	@GetMapping(path="/admin_login")
	public String adminLogin(RedirectAttributes redirectAttr) {
		if (!AuthenticationSystem.loggedOutOnly(redirectAttr)) {
			return "redirect:/";
		}
		
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
	public String sessionSuccess(Principal principal, RedirectAttributes redirectAttr) {
		String username = principal.getName();

		SafeUser user = userService.profile(username);
		RedirectMessageSystem.builder(redirectAttr)
			.add("환영합니다 " + user.getFirstName() + "님", Tags.SUCCESS)
			.build();
		return "redirect:/";
	}
	
	
	@GetMapping(path="/session_out")
	public String logout(RedirectAttributes redirectAttr) {
		RedirectMessageSystem.builder(redirectAttr)
			.add("다음에 만나요", Tags.INFO)
			.build();
		SecurityContextHolder.clearContext();
		return "redirect:/";
	}
	
}
