package com.clone.airbnb.security;

import org.springframework.security.core.context.SecurityContextHolder;

import com.clone.airbnb.dto.Authentication;

public class AuthenticationSystem {

	public static String getUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = null;

		if (principal != null) {
			if (principal instanceof Authentication) {
				Authentication auth = (Authentication) principal;
				username = auth.getUsername();
			} else {
				username = (String) principal;
			}
		}
		
		return username;
	}
	
	
	public static boolean isLogged() {
        final org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return null != authentication && !("anonymousUser").equals(authentication.getName());
    }
	
}
