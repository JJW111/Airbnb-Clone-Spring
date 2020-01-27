package com.clone.airbnb.creation;

import java.text.ParseException;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.entity.enu.LoginMethod;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.utils.DummyUtils;
import com.clone.airbnb.utils.WordUtils;
import com.github.javafaker.Faker;

/**
 * User Dummy 엔터티를 DB 에 지정한 개수만큼 생성한다.
 */
@SpringBootTest
class UserCreation {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final static int USER_CREATED = 200;
	
	Faker faker = new Faker(new Locale("en"));
	
	@Test
	void contextLoads() throws ParseException {
		final String PASSWORD 	= "abcd1234";
		final String EMAIL		= "@airbnb-clone.com";
		
		for (int i = 0; i < USER_CREATED; i++) {
			while(true) {
				final String username = faker.name().lastName().toLowerCase() + EMAIL;
				boolean exists = repository.existsByUsername(username);
			
				if (!exists) {
					User user = new User();
					user.setUsername(username);
					user.setPassword(passwordEncoder.encode(PASSWORD));
					user.setFirstName(faker.name().firstName());
					user.setLastName(faker.name().lastName());
					user.setBirthdate(faker.date().birthday());
					user.setBio(WordUtils.limit(faker.lorem().paragraph(), 50));
					user.setGender(DummyUtils.randomEnum(Gender.class));
					user.setLanguage(DummyUtils.randomEnum(Language.class));
					user.setCurrency(DummyUtils.randomEnum(Currency.class));
					user.setLoginMethod(LoginMethod.EMAIL);
					user.setSuperhost(DummyUtils.randomBoolean(0.5));
					user.setEmailVerified(true);
					user.setEmailSecret("");
					
					repository.save(user);
					
					break;
				}
			}
		}
		
	}
}
