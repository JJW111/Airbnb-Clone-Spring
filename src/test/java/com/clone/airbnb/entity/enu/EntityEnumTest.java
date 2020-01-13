package com.clone.airbnb.entity.enu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EntityEnumTest {

	@Test
	void valueTest() {
		Gender gender = Gender.valueOf("MALE");
		
		assertEquals("Male", gender.getValue());
	}
	
	
}
