package com.clone.airbnb.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.values.SelectValues;
import com.clone.airbnb.service.RoomService;
import com.clone.airbnb.service.UserService;

@Controller
@RequestMapping(path="/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private SelectValues selectValues;
	
	@GetMapping(path="/profile")
	public String profile(Principal principal, RedirectAttributes redirectAttr, Model model, @RequestParam(required = false, name = "id") Integer id) {
		User profile = null;
		
		if (id == null) {
			if (principal != null) {
				String username = principal.getName();
				profile = userService.profile(username);
			}
		} else {
			profile = userService.profile(id);
		}
		
		if (profile == null) {
			return "redirect:/wrong_access";
		} else {
			model.addAttribute("selectValues", selectValues);
			model.addAttribute("user", profile);
			model.addAttribute("rooms", roomService.rooms(profile.getUsername()));
			return "users/profile";
		}
	}
	
}
