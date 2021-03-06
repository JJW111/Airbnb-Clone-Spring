package com.clone.airbnb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clone.airbnb.dto.SignupDto;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.exception.AlreadyVerifiedUserException;
import com.clone.airbnb.exception.UserDoesNotExistsException;
import com.clone.airbnb.messages.RedirectMessageSystem;
import com.clone.airbnb.service.LoginService;
import com.clone.airbnb.service.UserService;

@Controller
public class SignUpController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@PreAuthorize("!isAuthenticated()")
	@GetMapping(path="signup")
	public String signup(Model model) {
		model.addAttribute("user", new SignupDto());
		return "users/signup";
	}
	
	
	
	@PreAuthorize("!isAuthenticated()")
	@PostMapping(path="signup")
	public String processSignup(Model model, RedirectAttributes redirectAttr, @Valid @ModelAttribute("user") SignupDto dto, BindingResult result) {
		if (!dto.getRetypePassword().equals(dto.getPassword())) {
			result.rejectValue("retypePassword", "password.retype.notequal");
		}

		if(result.hasErrors()){
			return "users/signup";
		}
		
		User user = dto.toOriginal(passwordEncoder);
		userService.signUp(user);
		
		loginService.login(user);
		
		RedirectMessageSystem.builder(redirectAttr)
			.info(user.getUsername() + " 으로 인증메일을 발송하였습니다.")
			.build();
		
		return "redirect:/";
	}
	
	
	@GetMapping(path="/verify/{secret}")
	public String verify(RedirectAttributes redirectAttr, @PathVariable("secret") String secret) {
		try {
			userService.verify(secret);
			RedirectMessageSystem.builder(redirectAttr)
				.success("이메일 인증을 완료하였습니다.")
				.build();
			return "redirect:/";
		} catch (UserDoesNotExistsException e) {
			return "users/fail_verification";
		} catch (AlreadyVerifiedUserException e) {
			return "redirect:/";
		}
	}
	
}
