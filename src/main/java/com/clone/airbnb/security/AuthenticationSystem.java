package com.clone.airbnb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clone.airbnb.dto.Authentication;
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.entity.enu.LoginMethod;
import com.clone.airbnb.messages.RedirectMessageSystem;
import com.clone.airbnb.repository.UserRepository;

@Component
public class AuthenticationSystem {

	@Autowired
	private UserRepository userRepository;
	
	
	
	public SafeUser getLoggedUser() {
		if (!isLogged()) return null;
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Authentication auth = (Authentication) principal;
		
		return userRepository.findByUsername(auth.getUsername(), SafeUser.class);
	}
	
	
	
	public LoginMethod getLoginMethodOfLoggedUser() {
		SafeUser user = getLoggedUser();
		if (user != null) {
			return user.getLoginMethod();
		} else {
			return null;
		}
	}
	
	
	
	public boolean isLoggedWithEmailUser() {
		LoginMethod method = getLoginMethodOfLoggedUser();
		
		if (method == null) {
			return false;
		} else {
			System.out.println(method + ":" + method.equals(LoginMethod.EMAIL));
			return method.equals(LoginMethod.EMAIL);
		}
	}
	
	
	
	public static boolean isLogged() {
        final org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return null != authentication && !("anonymousUser").equals(authentication.getName());
    }
	
	
	public static boolean loggedOutOnly(RedirectAttributes redirectAttr) {
		if (AuthenticationSystem.isLogged()) {
			RedirectMessageSystem.builder(redirectAttr)
				.add("이미 로그인 하였습니다.")
				.build();
			return false;
		}
		
		return true;
	}
	
	
	public boolean notLoggedSocial(RedirectAttributes redirectAttr) {
		if (!isLoggedWithEmailUser()) {
			RedirectMessageSystem.builder(redirectAttr)
				.add("소셜 로그인으로 접속한 경우 비밀번호 변경이 불가합니다.")
				.build();
			return false;
		}
		
		return true;
	}
	
}
