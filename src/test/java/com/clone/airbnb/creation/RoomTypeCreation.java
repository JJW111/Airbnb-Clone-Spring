package com.clone.airbnb.creation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.entity.RoomType;
import com.clone.airbnb.repository.RoomTypeRepository;

/**
 * RoomType Dummy 엔터티를 DB 에 지정한 개수만큼 생성한다.
 */
@SpringBootTest
class RoomTypeCreation {
	
	@Autowired
	private RoomTypeRepository repository;


	
	@Test
	void contextLoads() {
		String[] list = new String[] {
				"Entire place",
	            "Private room",
	            "Shared room",
	            "Hotel room",
		};
		
		for (String item : list) {
			if (!repository.existsByName(item)) {
				RoomType roomType = new RoomType();
				roomType.setName(item);
				
				repository.save(roomType);
			}
		}
	}
}
