package com.clone.airbnb.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Facility;

@EntityMapping(entity=Facility.class)
public interface FacilityRepository extends PagingAndSortingRepository<Facility, Integer> {
	boolean existsByName(String name);
	List<Facility> findAllByOrderByIdDesc();
}

