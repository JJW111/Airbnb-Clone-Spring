package com.clone.airbnb.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.clone.airbnb.entity.enu.LoginMethod;
import com.clone.airbnb.repository.UserRepository;

@PreAuthorize("isAuthenticated()")
@Component
public class AuthenticationSystem {

	@Autowired
	private UserRepository userRepository;
	
	
	
	public LoginMethod getLoginMethodOfLoggedUser(String username) {
		Optional<LoginMethod> opt = userRepository.loginMethodOfLoggedUser(username); 
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
	
	
	public boolean isLoggedWithEmailUser(String username) {
		LoginMethod method = getLoginMethodOfLoggedUser(username);
		
		if (method == null) {
			return false;
		} else {
			return method.equals(LoginMethod.EMAIL);
		}
	}
	
	
	
	public boolean notLoggedSocial(String username) {
		if (!isLoggedWithEmailUser(username)) {
			return false;
		} else {
			return true;
		}
	}
	
}
