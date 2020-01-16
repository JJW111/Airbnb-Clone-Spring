package com.clone.airbnb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Room;

@EntityMapping(entity=Room.class)
public interface RoomRepository extends PagingAndSortingRepository<Room, Integer> {
	
	Page<Room> findByOrderByIdAsc(Pageable pageable);
	
	Page<Room> findByOrderByIdDesc(Pageable pageable);
	
}
