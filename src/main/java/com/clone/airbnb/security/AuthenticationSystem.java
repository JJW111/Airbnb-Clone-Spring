package com.clone.airbnb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.clone.airbnb.dto.Authentication;
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.entity.enu.LoginMethod;
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
	
	
	public static boolean loggedOutOnly() {
		if (AuthenticationSystem.isLogged()) {
			return false;
		} else {
			return true;
		}
	}
	
	
	public boolean notLoggedSocial() {
		if (!isLoggedWithEmailUser()) {
			return false;
		} else {
			return true;
		}
	}
	
}
