package com.clone.airbnb.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.dto.PasswordChange;
import com.clone.airbnb.dto.UpdateProfileDto;
import com.clone.airbnb.entity.User;

public interface UserService extends UserDetailsService {
	void signUp(User user);
	void verify(String secret);
	User profile(String username);
	User profile(int id);
	User getUpdateProfile(String username);
	String	nickName(String username);
	void update(UpdateProfileDto dto);
	boolean matches(String username, PasswordChange passwordChange);
	void changePassowrd(String username, PasswordChange passwordChange);
	void uploadAvatar(MultipartFile file, String username);
	void deleteAvatar(String username);
}
