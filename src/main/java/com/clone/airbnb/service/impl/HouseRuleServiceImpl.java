package com.clone.airbnb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.airbnb.repository.HouseRuleRepository;
import com.clone.airbnb.service.HouseRuleService;

@Service
public class HouseRuleServiceImpl implements HouseRuleService {

	@SuppressWarnings("unused")
	@Autowired
	private HouseRuleRepository repository;
	
}
