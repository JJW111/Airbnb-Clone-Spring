package com.clone.airbnb.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.clone.airbnb.dto.Authentication;
import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.entity.enu.Role;
import com.clone.airbnb.repository.UserRepository;

@SpringBootTest(properties = "spring.config.location=" 
		+ "classpath:/application-test.yaml")
public class UserTest {
	
	@Autowired
	UserRepository repo;
	
	String username = "jjw@gmail.com";
	String password = "ablksdlkf123";
	String firstName = "Jin won";
	String lastName = "Jeong";
	Gender gender = Gender.OTHER;
	Language language = Language.KOREAN;
	Currency currency = Currency.KRW;
	Boolean superhost = false;
	Role role = Role.ADMIN;
	
	
	@Test
	void context() {
	}
	
	
	
	@AfterEach
	public void afterEach() {
		repo.deleteAll();
	}
	
	
	
	/**
	 * User가 DB에 에러없이 저장되는지 확인한다.
	 */
	@Test
	void saveTest() {
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
		
		repo.save(user);
	}
	
	
	
	
	/**
	 * <p>같은 id 값을 가진 엔터티를 save 하여 에러 없이 update 되는지 테스트한다.
	 */
	@Test
	void overrideTest() {
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
		
		User savedUser = repo.save(user);
		assertNotNull(savedUser);
		assertNotNull(savedUser.getId());
		
		User foundUser = repo.findById(savedUser.getId()).get();
		
		String newFirstName = "New First Name";
		String newLastName = "New Last Name";
		
		assertNotEquals(newFirstName, foundUser.getFirstName());
		assertNotEquals(newLastName, foundUser.getLastName());
		
		User user2 = User.builder()
				.setFirstName(newFirstName)
				.setLastName(newLastName)
				.build();
		
		foundUser.override(user2);
		
		User updatedUser = repo.save(foundUser);
		
		User foundUser2 = repo.findById(updatedUser.getId()).get();
		
		assertEquals(newFirstName, foundUser2.getFirstName());
		assertEquals(newLastName, foundUser2.getLastName());
	}
	
	
	
	@Test
	void existsByUsername() {
		boolean exists = repo.existsByUsername(username);
		assertFalse(exists);
		
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
		
		User savedUser = repo.save(user); // 에러 발생
		assertNotNull(savedUser);
		
		boolean exists2 = repo.existsByUsername(savedUser.getUsername());
		assertTrue(exists2);
	}
	
	
	
	/**
	 * <p>authenticate(String) 메서드를 테스트한다.
	 */
	@Test
	void authenticate() {
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
		
		User savedUser = repo.save(user);

		assertNotNull(savedUser.getId());
		assertNotNull(savedUser.getUsername());
		assertNotNull(savedUser.getPassword());
		assertNotNull(savedUser.getRole());
		
		Authentication auth = repo.authenticate(username);
		assertNotNull(auth);
		
		assertEquals(savedUser.getUsername(), auth.getUsername());
		assertEquals(savedUser.getPassword(), auth.getPassword());
		assertFalse(auth.getAuthorities().isEmpty());
	}
	
	
	
	@Test
	void findAll() {
		User user1 = User.builder()
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
		
		User savedUser1 = repo.save(user1);
		assertNotNull(savedUser1);
		
		String username2 = "jjw@naver.com";
		User user2 = User.builder()
				.setUsername(username2)
				.setPassword(password)
				.setFirstName(firstName)
				.setLastName(lastName)
				.setGender(gender)
				.setLanguage(language)
				.setCurrency(currency)
				.setSuperhost(superhost)
				.setRole(role)
				.build();
		
		User savedUser2 = repo.save(user2);
		assertNotNull(savedUser2);
		
		Page<SafeUser> page = repo.findAllBy(PageRequest.of(0, 2), SafeUser.class);
		assertNotEquals(0, page.getTotalElements());
		
		List<SafeUser> users = page.getContent();
		
		assertNotEquals(0, users.size());
	}
	
}
