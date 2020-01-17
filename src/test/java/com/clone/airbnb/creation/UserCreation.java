package com.clone.airbnb.creation;

import java.text.ParseException;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.entity.enu.LoginMethod;
import com.clone.airbnb.entity.enu.Role;
import com.clone.airbnb.repository.UserRepository;
import com.clone.airbnb.utils.DummyUtils;
import com.clone.airbnb.utils.WordUtils;
import com.github.javafaker.Faker;


@SpringBootTest
class UserCreation {
	
	@Autowired
	UserRepository repository;

	private final static int USER_CREATED = 200;
	
	Faker faker = new Faker(new Locale("en"));
	
	@Test
	void contextLoads() throws ParseException {
		final String PASSWORD 	= "abcd1234";
		final String EMAIL		= "@airbnb_clone.com";
		final Role ROLE			= Role.USER;
		
		for (int i = 0; i < USER_CREATED; i++) {
			while(true) {
				final String username = faker.name().lastName().toLowerCase() + EMAIL;
				boolean exists = repository.existsByUsername(username);
			
				if (!exists) {
					User admin = User.builder()
							.setUsername(username)
							.setPassword(PASSWORD)
							.setRole(ROLE)
							.setFirstName(faker.name().firstName())
							.setLastName(faker.name().lastName())
							.setBirthdate(faker.date().birthday())
							.setBio(WordUtils.limit(faker.lorem().paragraph(), 50))
							.setGender(DummyUtils.randomEnum(Gender.class))
							.setLanguage(DummyUtils.randomEnum(Language.class))
							.setCurrency(DummyUtils.randomEnum(Currency.class))
							.setLoginMethod(LoginMethod.EMAIL)
							.setSuperhost(DummyUtils.randomBoolean(0.5))
							.build();
					
					repository.save(admin);
					
					break;
				}
			}
		}
		
	}
}
