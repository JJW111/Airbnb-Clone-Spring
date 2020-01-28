package com.clone.airbnb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.clone.airbnb.entity.file.RoomPhoto;

public interface RoomPhotoRepository extends PagingAndSortingRepository<RoomPhoto, Integer> {
	Optional<RoomPhoto> findByIdAndRoomIdAndRoom_host_username(Integer id, Integer roomId, String username);
	Long countByRoomIdAndRoom_host_username(Integer roomId, String username);
	List<RoomPhoto> findByRoomId(Integer roomId);
}

