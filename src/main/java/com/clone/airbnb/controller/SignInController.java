package com.clone.airbnb.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clone.airbnb.messages.RedirectMessageSystem;
import com.clone.airbnb.service.UserService;



@Controller
public class SignInController {
	
	@Autowired
	private UserService userService;
	
	
	@PreAuthorize("!isAuthenticated()")
	@GetMapping(path="/login")
	public String login(RedirectAttributes redirectAttr) {
		return "users/login";
	}
	
	
	@PreAuthorize("!isAuthenticated()")
	@GetMapping(path="/admin_login")
	public String adminLogin(RedirectAttributes redirectAttr) {
		return "admin/login";
	}
	
	
	
	@PostMapping(path="/admin_session_fail")
	public String adminSessionFail() {
		return "admin/login";
	}
	
	
	
	@PostMapping(path="/session_fail")
	public String sessionFail() {
		return "users/login";
	}
	
	
	@GetMapping(path="/session_success")
	public String sessionSuccess(Principal principal, RedirectAttributes redirectAttr) {
		String username = principal.getName();

		String nickName = userService.nickName(username);
		RedirectMessageSystem.builder(redirectAttr)
			.success("환영합니다 " + nickName + "님")
			.build();
		return "redirect:/";
	}
	
	
	@GetMapping(path="/session_out")
	public String logout(RedirectAttributes redirectAttr) {
		RedirectMessageSystem.builder(redirectAttr)
			.info("다음에 만나요")
			.build();
		SecurityContextHolder.clearContext();
		return "redirect:/";
	}
	
}
