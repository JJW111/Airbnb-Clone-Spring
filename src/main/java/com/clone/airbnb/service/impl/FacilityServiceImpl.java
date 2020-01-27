package com.clone.airbnb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.airbnb.repository.FacilityRepository;
import com.clone.airbnb.service.FacilityService;

@Service
public class FacilityServiceImpl implements FacilityService {

	@SuppressWarnings("unused")
	@Autowired
	private FacilityRepository repository;

}
