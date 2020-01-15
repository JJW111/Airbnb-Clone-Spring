package com.clone.airbnb.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clone.airbnb.admin.AdminWebPage;
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.messages.Messages;
import com.clone.airbnb.service.UserService;

@Controller
@RequestMapping(path="/auth")
public class AuthController {

	@Autowired
	private AdminWebPage adminWebPage;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/update-profile")
	public String profile(Principal principal, Model model, RedirectAttributes redirectAttr) {
		String username = principal.getName();
		
		if (username != null) {
			SafeUser user = userService.profile(username);
			model.addAttribute("user", user);
			return "user/update_profile";
		} else {
			redirectAttr.addFlashAttribute("messages", Messages.builder()
					.add("Please login to update profile")
					.build());
			return "redirect:/user/profile";
		}
	}
	
	
	@PostMapping(path = "/update-profile")
	public String updateProfile(Model model, @Valid @ModelAttribute("user") User.Builder userBuilder, BindingResult result) {
		boolean hasErrors = adminWebPage.hasErrorsForUpdate("User", result);
		
		if (hasErrors) {
			return "user/update_profile";
		}
		
		userService.update(userBuilder.build());
		
		return "redirect:/user/profile";
	}
	
	
	@GetMapping(path ="/change-password")
	public String changePassword(Model model, RedirectAttributes redirectAttr) {
		return "user/change_password";
	}
	
	
}
