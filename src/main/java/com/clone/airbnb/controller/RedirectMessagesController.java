package com.clone.airbnb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clone.airbnb.messages.RedirectMessageSystem;



@Controller
public class RedirectMessagesController {
	
	@GetMapping(path="/logout_only")
	public String logoutOnly(RedirectAttributes redirectAttr, @RequestParam(required = false, name = "next") String next) {
		RedirectMessageSystem.builder(redirectAttr)
			.error("이미 로그인 하였습니다.")
			.build();
		
		if (next == null) {
			return "redirect:/";
		} else {
			return "redirect:" + next;
		}
	}
	
	
	@GetMapping(path="/change_password_email_only")
	public String emailOnly(RedirectAttributes redirectAttr) {
		RedirectMessageSystem.builder(redirectAttr)
			.error("소셜 로그인으로 접속한 경우 비밀번호 변경이 불가합니다.")
			.build();
		
		return "redirect:/user/profile";
	}
	
}
