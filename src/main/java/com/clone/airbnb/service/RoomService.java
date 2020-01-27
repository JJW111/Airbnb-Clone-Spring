package com.clone.airbnb.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.clone.airbnb.dto.RoomUpdateDto;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.entity.projection.RoomDetail;
import com.clone.airbnb.entity.projection.RoomList;
import com.clone.airbnb.entity.projection.RoomPhotos;
import com.clone.airbnb.entity.projection.RoomUpdate;

public interface RoomService {
	List<Room> all();
	Page<RoomList> rooms(Pageable pageable);
	List<RoomList> rooms(String username);
	void update(RoomUpdateDto dto, String username);
	List<Room> search(String city);
	RoomUpdate getRoomUpdate(Integer id, String username);
	RoomDetail getRoomDetail(Integer id);
	RoomPhotos getRoomPhotos(Integer roomId, String username);
	void deletePhoto(Integer roomId, Integer photoId, String username);
	void addPhoto(MultipartFile photoFile, Integer roomId, String username);
}
