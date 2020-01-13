package com.clone.airbnb.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clone.airbnb.entity.Room;

public interface RoomService {
	Page<Room> rooms(Pageable pageable); 
	List<Room> search(String city);
}
