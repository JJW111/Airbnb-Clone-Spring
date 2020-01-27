package com.clone.airbnb.creation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.entity.Amenity;
import com.clone.airbnb.repository.AmenityRepository;

/**
 * Amenity Dummy 엔터티를 DB 에 지정한 개수만큼 생성한다.
 */
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
				Amenity amenity = new Amenity();
				amenity.setName(item);
				
				repository.save(amenity);
			}
		}
	}
}
