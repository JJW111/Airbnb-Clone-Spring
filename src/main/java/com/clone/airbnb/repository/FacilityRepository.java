package com.clone.airbnb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Facility;

@EntityMapping(entity=Facility.class)
public interface FacilityRepository extends PagingAndSortingRepository<Facility, Integer> {
	
	boolean existsByName(String name);
	
	Facility findByName(String name);
	
	Page<Facility> findByOrderByIdAsc(Pageable pageable);
	
	Page<Facility> findByOrderByIdDesc(Pageable pageable);
	
}

