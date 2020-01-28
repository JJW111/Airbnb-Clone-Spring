package com.clone.airbnb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Room;

@EntityMapping(entity=Room.class)
public interface RoomRepository extends PagingAndSortingRepository<Room, Integer> {
	List<Room> findAllByOrderByIdDesc();
	Optional<Room> findByIdAndHost_username(Integer id, String username);
	List<Room> findAllByHost_username(String username);
}
