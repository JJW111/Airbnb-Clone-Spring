package com.clone.airbnb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.HouseRule;

@EntityMapping(entity=HouseRule.class)
public interface HouseRuleRepository extends PagingAndSortingRepository<HouseRule, Integer> {
	
	boolean existsByName(String name);
	
	HouseRule findByName(String name);
	
	Page<HouseRule> findByOrderByIdAsc(Pageable pageable);
	
	Page<HouseRule> findByOrderByIdDesc(Pageable pageable);
	
}

