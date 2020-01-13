package com.clone.airbnb.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.entity.enu.Role;
import com.clone.airbnb.repository.UserRepository;

@SpringBootTest(properties = "spring.config.location=" 
		+ "classpath:/application-test.yaml")
public class UserServiceTest {

	@Autowired
	UserService service;
	
	@Autowired
	UserRepository repository;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	String username = "jjw@gmail.com";
	String password = "ablksdlkf123";
	String firstName = "Jin won";
	String lastName = "Jeong";
	Gender gender = Gender.OTHER;
	Language language = Language.KOREAN;
	Currency currency = Currency.KRW;
	Boolean superhost = false;
	Role role = Role.ADMIN;
	
	
	
	@AfterEach
	void afterEach() { 
		repository.deleteAll();
	}
	
	
	
	@Test
	void loadByUsername() {
		User user = User.builder()
				.setUsername(username)
				.setPassword(password)
				.setFirstName(firstName)
				.setLastName(lastName)
				.setGender(gender)
				.setLanguage(language)
				.setCurrency(currency)
				.setSuperhost(superhost)
				.setRole(role)
				.build();
		
		repository.save(user);
		
		UserDetails userDetails = service.loadUserByUsername(username);
		assertNotNull(userDetails);
		
		assertEquals(username, userDetails.getUsername());
		assertTrue(passwordEncoder.matches(password, userDetails.getPassword()));
		assertFalse(userDetails.getAuthorities().isEmpty());
	}
	
}
