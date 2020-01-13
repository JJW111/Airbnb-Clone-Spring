package com.clone.airbnb.creation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.entity.RoomType;
import com.clone.airbnb.repository.RoomTypeRepository;


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
				RoomType roomType = RoomType.builder()
										.setName(item)
										.build();
				repository.save(roomType);
			}
		}
	}
}
