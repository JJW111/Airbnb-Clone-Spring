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

import com.clone.airbnb.dto.PasswordChange;
import com.clone.airbnb.dto.UpdateProfileDto;
import com.clone.airbnb.entity.projection.UpdateProfile;
import com.clone.airbnb.messages.RedirectMessageSystem;
import com.clone.airbnb.security.AuthenticationSystem;
import com.clone.airbnb.service.UserService;

@Controller
@RequestMapping(path="/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationSystem authSystem;
	
	
	@GetMapping(path = "/update-profile")
	public String profile(Principal principal, Model model, RedirectAttributes redirectAttr) {
		String username = principal.getName();
		
		if (username != null) {
			UpdateProfile updateProfile = userService.getUpdateProfile(username);
			model.addAttribute("user", updateProfile);
			return "users/update_profile";
		} else {
			RedirectMessageSystem.builder(redirectAttr)
				.error("프로필 변경을 위해 로그인하여 주십시오")
				.build();
			return "redirect:/users/profile";
		}
	}
	
	
	@PostMapping(path = "/update-profile")
	public String updateProfile(Model model, @Valid @ModelAttribute("user") UpdateProfileDto dto, BindingResult result, RedirectAttributes redirectAttr) {
		if (result.hasErrors()) {
			return "users/update_profile";
		}
		
		userService.update(dto);
		
		RedirectMessageSystem.builder(redirectAttr)
			.success("프로필을 업데이트 하였습니다")
			.build();
		
		return "redirect:/users/profile";
	}
	
	
	@GetMapping(path = "/change-password")
	public String changePassword(Principal principal, Model model, RedirectAttributes redirectAttr) {
		if (!authSystem.notLoggedSocial(principal.getName())) {
			return "redirect:/change_password_email_only";
		}
		
		model.addAttribute("passwordChange", new PasswordChange());
		return "users/change_password";
	}
	
	
	@PostMapping(path = "/change-password")
	public String updatePassword(Principal principal, @Valid @ModelAttribute("passwordChange") PasswordChange passwordChange, BindingResult result, 
			Model model, RedirectAttributes redirectAttr) {
		if (!authSystem.notLoggedSocial(principal.getName())) {
			return "redirect:/change_password_email_only";
		}
		
		if (!userService.matches(principal.getName(), passwordChange)) {
			result.rejectValue("oldPassword", "password.origin.notmatches");
		}
		
		if (!passwordChange.getRetypePassword().equals(passwordChange.getPassword())) {
			result.rejectValue("retypePassword", "password.retype.notequal");
		}
		
		if (result.hasErrors()) {
			return "users/change_password";
		}

		userService.changePassowrd(principal.getName(), passwordChange);
		
		RedirectMessageSystem.builder(redirectAttr)
			.success("비밀번호 변경 완료")
			.build();
		
		return "redirect:/users/profile";
	}
	
	
	@GetMapping(path = "/avatar")
	public String avatar(Principal principal, Model model) {
		model.addAttribute("user", userService.profile(principal.getName()));
		return "users/update_avatar";
	}
	
}
