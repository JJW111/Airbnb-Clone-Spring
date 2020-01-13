package com.clone.airbnb.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.repository.UserRepository;

/**
 * 테스트 목록
 * 	- Save
 *  - Delete
 * 	- Auto Increment Key
 * 	- Unique Column
 *  - Default Value
 */
@SpringBootTest(properties = "spring.config.location=" 
+ "classpath:/application-test.yaml")
class RepositoryTest {
	
	@Autowired
	UserRepository userRepository;
	
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
	
	
	
	@AfterEach
	void afterEach() {
		userRepository.deleteAll();
	}
	
	
	
	/**
	 * 테스트 내용
	 *  - save 
	 *  - findByUsername
	 *  - Auto Increment Key
	 */
	@Test
	void save() {
		User user = User.builder()
				.setUsername(username)
				.setPassword(password)
				.setFirstName(firstName)
				.setLastName(lastName)
				.setGender(gender)
				.setLanguage(language)
				.setCurrency(currency)
				.setSuperhost(superhost)
				.build();
		
		userRepository.save(user);
		
		User dbuser = userRepository.findByUsername(username);
		assertNotNull(dbuser);
		
		assertNotNull(dbuser.getId());
		assertEquals(Gender.OTHER, dbuser.getGender());
		assertEquals(username, dbuser.getUsername());
		assertTrue(passwordEncoder.matches(password, dbuser.getPassword()));
		assertEquals(language, dbuser.getLanguage());
		assertEquals(currency, dbuser.getCurrency());
	}

	
	
	/**
	 * 테스트 내용
	 *  - Unique Column
	 */
	@Test
	void uniqueColumnTest() {
		User user = User.builder()
				.setUsername(username)
				.setPassword(password)
				.setFirstName(firstName)
				.setLastName(lastName)
				.setGender(gender)
				.setLanguage(language)
				.setCurrency(currency)
				.setSuperhost(superhost)
				.build();
	
		userRepository.save(user);
		
		User user2 = User.builder()
				.setUsername(username)
				.setPassword(password)
				.setFirstName(firstName)
				.setLastName(lastName)
				.setGender(gender)
				.setLanguage(language)
				.setCurrency(currency)
				.setSuperhost(superhost)
				.build();
		
		Assertions.assertThrows(
				DataIntegrityViolationException.class,
				() -> { userRepository.save(user2); }
				);
	}
	
	
}
