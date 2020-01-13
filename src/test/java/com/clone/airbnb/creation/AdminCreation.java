package com.clone.airbnb.creation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.dto.SafeUser;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.entity.enu.Role;
import com.clone.airbnb.repository.UserRepository;


@SpringBootTest
class AdminCreation {
	
	@Autowired
	UserRepository repository;

	
	
	@Test
	void contextLoads() {
		String 	ADMIN_USERNAME 	= "admin@airbnb_clone.com";
		String 	ADMIN_PASSWORD 	= "abcd1234";
		Role	ADMIN_ROLE		= Role.ADMIN;
		
		SafeUser alreadyExistsAdmin = repository.findByUsername(ADMIN_USERNAME, SafeUser.class);
		
		if (alreadyExistsAdmin == null) {
			User admin = User.builder()
					.setUsername(ADMIN_USERNAME)
					.setPassword(ADMIN_PASSWORD)
					.setRole(ADMIN_ROLE)
					.setFirstName("admin")
					.setLastName("#")
					.setGender(Gender.MALE)
					.setLanguage(Language.KOREAN)
					.setCurrency(Currency.KRW)
					.setSuperhost(false)
					.build();
			
			repository.save(admin);
		}
	}
}
