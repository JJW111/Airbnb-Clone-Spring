package com.clone.airbnb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.clone.airbnb.service.UserService;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserDetails details = userService.loadUserByUsername(name);
		
		if (password.isEmpty() || details == null || !passwordEncoder.matches(password, details.getPassword())) {
			throw new BadCredentialsException("Username: " + name + ", password is incorect!");
		}
		
		return new UsernamePasswordAuthenticationToken(name, password, details.getAuthorities());
	}

	
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
