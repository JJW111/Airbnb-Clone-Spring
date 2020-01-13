package com.clone.airbnb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
	public void signUp(User user) {
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
			System.out.println("확인 메일 발송 중 에러 발생! Error: " + e);
		}
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
	
}
