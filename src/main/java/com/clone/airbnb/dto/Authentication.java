package com.clone.airbnb.dto;


import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.clone.airbnb.entity.enu.Role;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class Authentication extends User {
	
	@Getter
	public static class Builder {
		
		private String username;
		private String password;
		private Role role;
			
		
		
		public Builder setUsername(String username) {
			this.username = username;
			return this;
		}
		
		
		
		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}
		
		
		
		public Builder setRole(Role role) {
			this.role = role;
			return this;
		}
		
		
		
		public Authentication build() {
			return new Authentication(this);
		}
		
	}
	
	
	
	
	public static Builder authBuilder() {
		return new Authentication.Builder();
	}
	
	
	
	
	private Authentication(Builder builder) {
		super(builder.getUsername(), builder.getPassword(), makeGrantedAuthorities(builder.getRole()));
	}
	
	
	
	public Authentication(String username, String password, Role role) {
		this(authBuilder().setUsername(username).setPassword(password).setRole(role));
	}
	
	
	
	private static List<GrantedAuthority> makeGrantedAuthorities(Role role) {
		List<GrantedAuthority> list = new ArrayList<>();
		if (Role.ADMIN == role) {
			list.add(new SimpleGrantedAuthority(Role.USER.getValue()));
		}
		list.add(new SimpleGrantedAuthority(role.getValue()));
		return list;
	}
	
}
