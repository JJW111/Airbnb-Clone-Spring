package com.clone.airbnb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.service.FileService;
import com.clone.airbnb.service.MailService;
import com.clone.airbnb.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private FileService fileService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.authenticate(username);
	}

	
	
	@Override
	public User signUp(User user) {
		// 메일 발송 처리를 한다.
		userRepository.save(user);
		
		if (user.getAvatar() != null) {
			fileService.save(user.getAvatar());
		}
		
		String subject = "Verify Airbnb Account";
		String text = "To Verify Account click <a href='http://localhost:8081/user/verify/" + user.getEmailSecret() + "'>here</a>";
		
		try {
			mailService.sendMail(user.getUsername(), subject, text);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return user;
	}
	
	
	
	@Override
	public boolean verify(String secret) {
		User user = userRepository.findByEmailSecret(secret);
		
		if (user == null) {
			return false;
		} else {
			User verifiedUser = user.toBuilder()
								.setEmailVerified(true)
								.build();
			userRepository.save(verifiedUser);
			return true;
		}
	}
	
	
	
	@Override
	public SafeUser profile(String username) {
		return userRepository.findByUsername(username, SafeUser.class);
	}
	
	
	
	@Override
	public SafeUser profile(int id) {
		return userRepository.findById(id, SafeUser.class);
	}
	
	
	
	@Override
	public void update(User user) {
		User origin = userRepository.findById(user.getId()).get();
		user = user.toBuilder()
			.setAvatar(origin.getAvatar())
			.setEmailSecret(origin.getEmailSecret())
			.setEmailVerified(origin.getEmailVerified())
			.setLoginMethod(origin.getLoginMethod())
			.setSuperhost(origin.getSuperhost())
			.build();
		origin.override(user);
		userRepository.save(origin);
	}
	
}
