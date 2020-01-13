package com.clone.airbnb.creation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.entity.Facility;
import com.clone.airbnb.repository.FacilityRepository;


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
				Facility facility = Facility.builder()
										.setName(item)
										.build();
				repository.save(facility);
			}
		}
	}
}
