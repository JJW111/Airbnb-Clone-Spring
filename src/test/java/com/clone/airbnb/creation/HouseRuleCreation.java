package com.clone.airbnb.creation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.entity.HouseRule;
import com.clone.airbnb.repository.HouseRuleRepository;


@SpringBootTest
class HouseRuleCreation {
	
	@Autowired
	private HouseRuleRepository repository;


	
	@Test
	void contextLoads() {
		String[] list = new String[] {
			"Don't smoke",
			"Pets not allowed",
		};
		
		for (String item : list) {
			if (!repository.existsByName(item)) {
				HouseRule houseRule = HouseRule.builder()
										.setName(item)
										.build();
				repository.save(houseRule);
			}
		}
	}
}
