package com.clone.airbnb.utils;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.entity.enu.Gender;

@SpringBootTest
public class DummyUtilsTest {

	@Test
	void randomEnum() {
		for (int i = 0; i < 10; i++) {
			System.out.println(DummyUtils.randomEnum(Gender.class));
		}
	}
	
	@Test
	void randomBoolean() {
		for (int i = 0; i < 10; i++) {
			System.out.println(DummyUtils.randomBoolean(0.9));
		}
	}
	
}
