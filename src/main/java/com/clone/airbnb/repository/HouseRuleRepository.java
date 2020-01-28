package com.clone.airbnb.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.HouseRule;

@EntityMapping(entity=HouseRule.class)
public interface HouseRuleRepository extends PagingAndSortingRepository<HouseRule, Integer> {
	boolean existsByName(String name);
	List<HouseRule> findAllByOrderByIdDesc();
}

