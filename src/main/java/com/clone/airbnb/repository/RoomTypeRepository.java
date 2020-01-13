package com.clone.airbnb.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.RoomType;

@EntityMapping(entity=RoomType.class)
public interface RoomTypeRepository extends PagingAndSortingRepository<RoomType, Integer> {
	
	boolean existsByName(String name);
	
	Page<RoomType> findByOrderByIdAsc(Pageable pageable);
	
	Page<RoomType> findByOrderByIdDesc(Pageable pageable);
	
}

