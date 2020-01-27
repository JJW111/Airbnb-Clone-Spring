package com.clone.airbnb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clone.airbnb.repository.RoomTypeRepository;
import com.clone.airbnb.service.RoomTypeService;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

	@SuppressWarnings("unused")
	@Autowired
	private RoomTypeRepository repository;
	
}
