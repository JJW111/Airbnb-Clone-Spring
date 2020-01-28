package com.clone.airbnb.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clone.airbnb.dto.Authentication;
import com.clone.airbnb.dto.PasswordChange;
import com.clone.airbnb.dto.UpdateProfileDto;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.exception.AlreadyVerifiedUserException;
import com.clone.airbnb.exception.FailedToSendMailException;
import com.clone.airbnb.exception.UserDoesNotExistsException;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.service.MailService;
import com.clone.airbnb.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Authentication> opt = userRepository.authenticate(username);
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	
	
	@Override
	public void signUp(User user) {
		// 메일 발송 처리를 한다.
		userRepository.save(user);
		
		String subject = "Verify Airbnb Account";
		String text = "To Verify Account click <a href='http://localhost:8081/verify/" + user.getEmailSecret() + "'>here</a>";
		
		try {
			mailService.sendMail(user.getUsername(), subject, text);
		} catch (Exception e) {
			throw new FailedToSendMailException(e);
		}
		
	}
	
	
	
	@Override
	public void verify(String secret) {
		Optional<User> opt = userRepository.findByEmailSecret(secret);
		
		if (!opt.isPresent()) {
			throw new UserDoesNotExistsException("존재하지 않는 사용자입니다.");
		}
		
		User user = opt.get();
		
		if (user.getEmailVerified()) {
			throw new AlreadyVerifiedUserException("이미 인증된 유저입니다.");
		}
		
		user.setEmailVerified(true);
		user.setEmailSecret("");

		userRepository.save(user);
	}
	
	
	
	@Override
	public User profile(String username) {
		Optional<User> opt = userRepository.findByUsername(username);
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
	
	
	@Override
	public User profile(int id) {
		Optional<User> opt = userRepository.findById(id);
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
	
	
	@Override
	public String nickName(String username) {
		Optional<String> opt = userRepository.firstName(username);
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
	
	
	@Override
	public User getUpdateProfile(String username) {
		Optional<User> opt = userRepository.findByUsername(username);
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
	
	
	@Override
	public void update(UpdateProfileDto dto) {
		Optional<User> opt = userRepository.findById(dto.getId());
		
		if (!opt.isPresent()) {
			throw new UserDoesNotExistsException("id[" + dto.getId() + "] 에 해당하는 유저가 존재하지 않습니다.");
		}
		
		User old = opt.get();

		old.setFirstName(dto.getFirstName());
		old.setLastName(dto.getLastName());
		old.setGender(dto.getGender());
		old.setBio(dto.getBio());
		old.setBirthdate(dto.getBirthdate());
		old.setLanguage(dto.getLanguage());
		old.setCurrency(dto.getCurrency());
		
		userRepository.save(old);
	}
	
	
	@Override
	public boolean matches(String username, PasswordChange passwordChange) {
		Optional<String> opt = userRepository.password(username);

		if (!opt.isPresent()) {
			throw new UserDoesNotExistsException("username[" + username + "] 에 해당하는 유저가 존재하지 않습니다.");
		}
		
		String dbPassword = opt.get();
		
		if (!passwordEncoder.matches(passwordChange.getOldPassword(), dbPassword)) {
			return false;
		}
		
		return true;
	}
	
	
	@Override
	public void changePassowrd(String username, PasswordChange passwordChange) {
		Optional<User> opt =  userRepository.findByUsername(username);
		
		if (!opt.isPresent()) {
			throw new UserDoesNotExistsException("username[" + username + "] 에 해당하는 유저가 존재하지 않습니다.");
		}
		
		User user = opt.get();
		user.setPassword(passwordEncoder.encode(passwordChange.getPassword()));
		userRepository.save(user);
	}
	
}
