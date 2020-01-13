package com.clone.airbnb.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.entity.User;
import com.clone.airbnb.utils.ReflectionInvocator;

@SpringBootTest
public class ReflectionInvocatorTest {

	@Test
	void getTest() {
		String username = "jjw@gmail.com";
		User user = User.builder()
				.setUsername(username)
				.build();
		
		String gotUsername = (String) ReflectionInvocator.get(user, "username");
		
		assertEquals(username, gotUsername);
	}
	
}
