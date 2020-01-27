package com.clone.airbnb.creation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.Role;
import com.clone.airbnb.repository.UserRepository;

/**
 * Admin 계정을 생성한다.
 */
@SpringBootTest
class AdminCreation {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Test
	void contextLoads() {
		String 	ADMIN_USERNAME 	= "admin@airbnb-clone.com";
		String 	ADMIN_PASSWORD 	= "abcd1234";
		String 	BIO 			= "관리자 계정입니다.";
		Role	ADMIN_ROLE		= Role.ADMIN;
		
		if (!repository.existsByUsername(ADMIN_USERNAME)) {
			User admin = new User();
			admin.setUsername(ADMIN_USERNAME);
			admin.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
			admin.setBio(BIO);
			admin.setRole(ADMIN_ROLE);
			admin.setFirstName("admin");
			admin.setLastName("#");
			admin.setEmailVerified(true);
			admin.setEmailSecret("");

			repository.save(admin);
		}
	}
}
