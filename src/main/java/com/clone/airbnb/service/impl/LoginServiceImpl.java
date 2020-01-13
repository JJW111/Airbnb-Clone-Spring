package com.clone.airbnb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.clone.airbnb.entity.User;
import com.clone.airbnb.service.LoginService;
import com.clone.airbnb.service.UserService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserService userService;
	
	@Override
	public void login(User user) {
		UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
	}

}
