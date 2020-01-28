package com.clone.airbnb.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Amenity;

@EntityMapping(entity=Amenity.class)
public interface AmenityRepository extends PagingAndSortingRepository<Amenity, Integer> {
	boolean existsByName(String name);
	List<Amenity> findAllByOrderByIdDesc();
}

