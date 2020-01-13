package com.clone.airbnb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Review;

@EntityMapping(entity=Review.class)
public interface ReviewRepository extends PagingAndSortingRepository<Review, Integer> {
	
	Page<Review> findByOrderByIdAsc(Pageable pageable);
	
	Page<Review> findByOrderByIdDesc(Pageable pageable);
	
}

