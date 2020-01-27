package com.clone.airbnb.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.clone.airbnb.dto.PasswordChange;
import com.clone.airbnb.dto.UpdateProfileDto;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.projection.Profile;
import com.clone.airbnb.entity.projection.UpdateProfile;


public interface UserService extends UserDetailsService {
	void signUp(User user);
	void verify(String secret);
	Profile profile(String username);
	Profile profile(int id);
	UpdateProfile getUpdateProfile(String username);
	String	nickName(String username);
	void update(UpdateProfileDto dto);
	boolean matches(String username, PasswordChange passwordChange);
	void changePassowrd(String username, PasswordChange passwordChange);
}
