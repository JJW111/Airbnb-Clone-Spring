package com.clone.airbnb.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.RoomType;

@EntityMapping(entity=RoomType.class)
public interface RoomTypeRepository extends PagingAndSortingRepository<RoomType, Integer> {
	boolean existsByName(String name);
	List<RoomType> findAllByOrderByIdDesc();
}

