package com.clone.airbnb.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.messages.Messages;
import com.clone.airbnb.service.RoomService;
import com.clone.airbnb.service.UserService;

@Controller
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path="/profile")
	public String profile(Principal principal, RedirectAttributes redirectAttr, Model model, @RequestParam(required = false, name = "id") Integer id) {
		SafeUser user = null;
		
		if (id == null) {
			if (principal != null) {
				String username = principal.getName();
				user = userService.profile(username);
			}
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
