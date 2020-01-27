package com.clone.airbnb.creation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.entity.Facility;
import com.clone.airbnb.repository.FacilityRepository;

/**
 * Facility Dummy 엔터티를 DB 에 지정한 개수만큼 생성한다.
 */
@SpringBootTest
class FacilityCreation {
	
	@Autowired
	private FacilityRepository repository;


	
	@Test
	void contextLoads() {
		String[] list = new String[] {
			"Private entrance",
            "Paid parking on premises",
            "Paid parking off premises",
            "Elevator",
            "Parking",
            "Gym",
		};
		
		for (String item : list) {
			if (!repository.existsByName(item)) {
				Facility facility = new Facility();
				facility.setName(item);
				
				repository.save(facility);
			}
		}
	}
}
