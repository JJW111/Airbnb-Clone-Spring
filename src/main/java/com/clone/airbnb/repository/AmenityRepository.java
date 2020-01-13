package com.clone.airbnb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Amenity;

@EntityMapping(entity=Amenity.class)
public interface AmenityRepository extends PagingAndSortingRepository<Amenity, Integer> {
	
	boolean existsByName(String name);
	
	Amenity findByName(String name);
	
	Page<Amenity> findByOrderByIdAsc(Pageable pageable);
	
	Page<Amenity> findByOrderByIdDesc(Pageable pageable);
	
}

