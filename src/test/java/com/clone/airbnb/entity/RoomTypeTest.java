package com.clone.airbnb.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.repository.RoomTypeRepository;

@SpringBootTest(properties = "spring.config.location=" 
		+ "classpath:/application-test.yaml")
public class RoomTypeTest {

	@Autowired
	RoomTypeRepository repository;
	
	@AfterEach
	void afterEach() {
		repository.deleteAll();
	}
	
	@Test
	void findById() {
		RoomType roomType = RoomType.builder()
				.setName("Private Room")
				.build();
		
		RoomType savedRoomType = repository.save(roomType);
		assertNotNull(savedRoomType);
		assertNotNull(savedRoomType.getId());
		
		RoomType foundRoomType = repository.findById(savedRoomType.getId()).get();
		assertNotNull(foundRoomType);
		assertEquals(savedRoomType.getId(), foundRoomType.getId());
		assertEquals(savedRoomType.getName(), foundRoomType.getName());
	}
	
}
