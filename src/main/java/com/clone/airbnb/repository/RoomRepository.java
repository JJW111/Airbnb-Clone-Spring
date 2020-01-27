package com.clone.airbnb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Room;

@EntityMapping(entity=Room.class)
public interface RoomRepository extends PagingAndSortingRepository<Room, Integer> {
	<T> Optional<T> findById(Integer id, Class<T> clazz);
	List<Room> findAllByOrderByIdDesc();
	<T> List<T> findAllBy(Class<T> clazz);
	<T> Page<T> findAllBy(Pageable pageable, Class<T> clazz); 
	<T> List<T> findAllByHost_username(String username, Class<T> clazz);
	Optional<Room> findByIdAndHost_username(Integer id, String username);
	<T> Optional<T> findByIdAndHost_username(Integer id, String username, Class<T> clazz);
}
