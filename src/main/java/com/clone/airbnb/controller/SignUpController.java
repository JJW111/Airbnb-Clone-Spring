package com.clone.airbnb.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.messages.Messages;
import com.clone.airbnb.service.LoginService;
import com.clone.airbnb.service.UserService;

@Controller
public class SignUpController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping(path="signup")
	public String signup(Model model) {
		model.addAttribute("user", User.builder());
		model.addAttribute("genderValues", Gender.values());
		model.addAttribute("languageValues", Language.values());
		model.addAttribute("currencyValues", Currency.values());
		return "user/signup";
	}
	
	
	@PostMapping(path="signup")
	public String processSignup(RedirectAttributes redirectAttr, @Valid @ModelAttribute("user") User.Builder userBuilder, BindingResult result, Model model) {
		if (!userBuilder.getRetypePassword().equals(userBuilder.getPassword())) {
			result.rejectValue("retypePassword", "password.retype.notequal");
		}

		if(result.hasErrors()){
			model.addAttribute("genderValues", Gender.values());
			model.addAttribute("languageValues", Language.values());
			model.addAttribute("currencyValues", Currency.values());
			return "user/signup";
		}
		
		User user = userService.signUp(userBuilder.build());
		if (user != null) {
			loginService.login(user);
		}
		
		redirectAttr.addFlashAttribute("messages", Messages.builder()
				.add("Welcome " + user.getFirstName())
				.build());
		
		return "redirect:/";
	}
	
	
	@GetMapping(path="user/verify/{secret}")
	public String verify(@PathVariable("secret") String secret) {
		if (userService.verify(secret)) {
			return "redirect:/";
		} else {
			return "user/fail_verification";
		}
	}
	
}
