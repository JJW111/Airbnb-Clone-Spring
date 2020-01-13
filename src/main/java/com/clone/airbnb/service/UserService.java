package com.clone.airbnb.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.clone.airbnb.entity.User;


public interface UserService extends UserDetailsService {
	void signUp(User user);
	boolean verify(String secret);
}
