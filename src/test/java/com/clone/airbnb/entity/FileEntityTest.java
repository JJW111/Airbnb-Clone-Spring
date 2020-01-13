package com.clone.airbnb.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.entity.file.Avatar;
import com.clone.airbnb.utils.ReflectionInvocator;

@SpringBootTest
public class FileEntityTest {

	@Test
	void callSuperEquals() {
		Integer id = 1;
		
		Avatar avatar = Avatar.builder()
				.setId(id)
				.build();
		
		Avatar avatar2 = Avatar.builder()
				.setId(id)
				.build();
		
		assertEquals(avatar, avatar2);
	}
	
	
	@Test
	void getFieldWithReflection() {
		Integer id = 1;
		
		Avatar avatar = Avatar.builder()
				.setId(id)
				.build();
		
		Avatar avatar2 = Avatar.builder()
				.setId(id)
				.build();
		
		assertEquals(avatar, avatar2);
		User user = User.builder()
				.setAvatar(avatar)
				.build();
		
		User user2 = User.builder()
				.setAvatar(avatar2)
				.build();
		
		Object o = ReflectionInvocator.get(user, "avatar");
		Object o2 = ReflectionInvocator.get(user2, "avatar");
		
		assertEquals(o, o2);
	}
	
}
