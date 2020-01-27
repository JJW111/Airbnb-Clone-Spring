package com.clone.airbnb.creation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.entity.HouseRule;
import com.clone.airbnb.repository.HouseRuleRepository;

/**
 * HouseRule Dummy 엔터티를 DB 에 지정한 개수만큼 생성한다.
 */
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
				HouseRule houseRule = new HouseRule();
				houseRule.setName(item);
				
				repository.save(houseRule);
			}
		}
	}
}
