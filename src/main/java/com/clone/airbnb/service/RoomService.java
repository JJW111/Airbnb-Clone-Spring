package com.clone.airbnb.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.dto.RoomUpdateDto;
import com.clone.airbnb.entity.Room;

public interface RoomService {
	List<Room> all();
	Page<Room> rooms(Pageable pageable);
	List<Room> rooms(String username);
	void updateRoom(RoomUpdateDto dto, String username);
	List<Room> search(String city);
	Room getRoomUpdate(Integer id, String username);
	Room getRoomDetail(Integer id);
	Room getRoomPhotos(Integer roomId, String username);
	void deletePhoto(Integer roomId, Integer photoId, String username);
	void addPhoto(MultipartFile photoFile, Integer roomId, String username);
	void addRoom(Room room, String username);
	void deleteRoom(Integer roomId, String username);
}
