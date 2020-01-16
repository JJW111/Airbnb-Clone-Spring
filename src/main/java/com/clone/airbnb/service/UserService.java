package com.clone.airbnb.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.clone.airbnb.dto.PasswordChange;
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.entity.User;


public interface UserService extends UserDetailsService {
	User signUp(User user);
	boolean verify(String secret);
	SafeUser profile(String username);
	SafeUser profile(int id);
	void update(User user);
	boolean matches(String username, PasswordChange passwordChange);
	void changePassowrd(String username, PasswordChange passwordChange);
}
