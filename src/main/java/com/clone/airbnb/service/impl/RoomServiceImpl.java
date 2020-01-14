package com.clone.airbnb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clone.airbnb.entity.Room;
import com.clone.airbnb.repository.RoomRepository;
import com.clone.airbnb.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Override
	public Page<Room> rooms(Pageable pageable) {
		return roomRepository.findByOrderByIdDesc(pageable);
	}

	
	
	@Override
	public List<Room> search(String city) {
		return null;
	}
	
	
	
	@Override
	public Room get(int id) {
		return roomRepository.findById(id).get();
	}
	
}
