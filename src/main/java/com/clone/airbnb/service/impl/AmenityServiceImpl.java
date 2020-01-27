package com.clone.airbnb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.airbnb.repository.AmenityRepository;
import com.clone.airbnb.service.AmenityService;

@Service
public class AmenityServiceImpl implements AmenityService {

	@SuppressWarnings("unused")
	@Autowired
	private AmenityRepository repository;
	
}
