package com.clone.airbnb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clone.airbnb.dto.PasswordChange;
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.exception.AlreadyVerifiedUserException;
import com.clone.airbnb.exception.UserDoesNotExistsException;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.authenticate(username);
	}

	
	
	@Override
	public User signUp(User user) {
		// 메일 발송 처리를 한다.
		userRepository.save(user);
		
		String subject = "Verify Airbnb Account";
		String text = "To Verify Account click <a href='http://localhost:8081/verify/" + user.getEmailSecret() + "'>here</a>";
		
		try {
			mailService.sendMail(user.getUsername(), subject, text);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return user;
	}
	
	
	
	@Override
	public void verify(String secret) {
		User user = userRepository.findByEmailSecret(secret);
		
		if (user == null) {
			throw new UserDoesNotExistsException("존재하지 않는 사용자입니다.");
		} else {
			if (user.getEmailVerified()) {
				throw new AlreadyVerifiedUserException("이미 인증된 유저입니다.");
			}
			User verifiedUser = user.toBuilder()
								.setEmailVerified(true)
								.setEmailSecret("")
								.build();
			userRepository.save(verifiedUser);
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
	
	
	@Override
	public boolean matches(String username, PasswordChange passwordChange) {
		String dbPassword = userRepository.password(username);

		if (!passwordEncoder.matches(passwordChange.getOldPassword(), dbPassword)) {
			return false;
		}
		
		return true;
	}
	
	
	@Override
	public void changePassowrd(String username, PasswordChange passwordChange) {
		User user = userRepository.findByUsername(username);
		
		User changed = user.toBuilder()
			.setPassword(passwordEncoder.encode(passwordChange.getPassword()))
			.build();
		
		userRepository.save(changed);
	}
	
}
