package com.clone.airbnb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clone.airbnb.entity.User;
import com.clone.airbnb.exception.GithubException;
import com.clone.airbnb.exception.GithubPrivateEmailException;
import com.clone.airbnb.exception.KakaoEmailDoesNotExistException;
import com.clone.airbnb.exception.KakaoException;
import com.clone.airbnb.messages.RedirectMessageSystem;
import com.clone.airbnb.security.AuthenticationSystem;
import com.clone.airbnb.service.GithubLoginService;
import com.clone.airbnb.service.KakaoLoginService;
import com.clone.airbnb.service.LoginService;


@Controller
@RequestMapping(path="/login")
public class SocialLoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private GithubLoginService ghService;
	
	@Autowired
	private KakaoLoginService kakaoService;
	
	
	@GetMapping(path="/github")
	public String githubLogin(RedirectAttributes redirectAttr) {
		if (!AuthenticationSystem.loggedOutOnly()) {
			return "redirect:/logout_only";
		}
		
		return "redirect:" + ghService.identityUrl();
	}
	
	
	
	@GetMapping(path="/github/callback")
	public String githubCallback(RedirectAttributes redirectAttr, @RequestParam("code") String code) {
		try {
			User user = ghService.authorize(code);
			if (user != null) {
				loginService.login(user);
			}
			return "redirect:/session_success";
		} catch (GithubPrivateEmailException e) {
			RedirectMessageSystem.builder(redirectAttr)
				.error(e.getMessage())
				.build();
			return "redirect:/login";
		} catch (GithubException e) {
			RedirectMessageSystem.builder(redirectAttr)
				.error(e.getMessage())
				.build();
			return "redirect:/login";
		}
	}
	
	

	@GetMapping(path="/kakao")
	public String kakaoLogin(RedirectAttributes redirectAttr) {
		if (!AuthenticationSystem.loggedOutOnly()) {
			return "redirect:/logout_only";
		}
		
		return "redirect:" + kakaoService.identityUrl();
	}
	
	
	@GetMapping(path="/kakao/callback")
	public String kakaoCallback(RedirectAttributes redirectAttr, @RequestParam("code") String code) {
		try {
			User user = kakaoService.authorize(code);
			if (user != null) {
				loginService.login(user);
			}
			return "redirect:/session_success";
		} catch (KakaoEmailDoesNotExistException e) {
			RedirectMessageSystem.builder(redirectAttr)
				.error(e.getMessage())
				.build();
			return "redirect:/login";
		} catch (KakaoException e) {
			RedirectMessageSystem.builder(redirectAttr)
				.error(e.getMessage())
				.build();
			return "redirect:/login";
		}
	}
	
}
