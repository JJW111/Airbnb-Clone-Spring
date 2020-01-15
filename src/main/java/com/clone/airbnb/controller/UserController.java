package com.clone.airbnb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clone.airbnb.dto.Authentication;
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.messages.Messages;
import com.clone.airbnb.service.UserService;

@Controller
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(path="/profile")
	public String profile(RedirectAttributes redirectAttr, Model model, @RequestParam(required = false, name = "id") Integer id) {
		SafeUser user = null;
		
		if (id == null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = null;
			if (principal instanceof Authentication) {
				Authentication auth = (Authentication) principal;
				username = auth.getUsername();
			} else {
				username = (String) principal;
			}
			
			user = userService.profile(username);
		} else {
			user = userService.profile(id);
		}
		
		if (user == null) {
			redirectAttr.addFlashAttribute("messages", Messages.builder()
					.add("잘못된 접근입니다.")
					.build());
			return "redirect:/";
		} else {
			model.addAttribute("user", user);
			return "user/profile";
		}
	}
	
}
