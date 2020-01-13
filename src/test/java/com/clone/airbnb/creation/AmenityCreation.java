package com.clone.airbnb.creation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.entity.Amenity;
import com.clone.airbnb.repository.AmenityRepository;


@SpringBootTest
class AmenityCreation {
	
	@Autowired
	private AmenityRepository repository;


	
	@Test
	void contextLoads() {
		String[] list = new String[] {
				"Air conditioning",
	            "Alarm Clock",
	            "Balcony",
	            "Bathroom",
	            "Bathtub",
	            "Bed Linen",
	            "Boating",
	            "Cable TV",
	            "Carbon monoxide detectors",
	            "Chairs",
	            "Children Area",
	            "Coffee Maker in Room",
	            "Cooking hob",
	            "Cookware & Kitchen Utensils",
	            "Dishwasher",
	            "Double bed",
	            "En suite bathroom",
	            "Free Parking",
	            "Free Wireless Internet",
	            "Freezer",
	            "Fridge / Freezer",
	            "Golf",
	            "Hair Dryer",
	            "Heating",
	            "Hot tub",
	            "Indoor Pool",
	            "Ironing Board",
	            "Microwave",
	            "Outdoor Pool",
	            "Outdoor Tennis",
	            "Oven",
	            "Queen size bed",
	            "Restaurant",
	            "Shopping Mall",
	            "Shower",
	            "Smoke detectors",
	            "Sofa",
	            "Stereo",
	            "Swimming pool",
	            "Toilet",
	            "Towels",
	            "TV",
		};
		
		for (String item : list) {
			if (!repository.existsByName(item)) {
				Amenity amenity = Amenity.builder()
										.setName(item)
										.build();
				repository.save(amenity);
			}
		}
	}
}
