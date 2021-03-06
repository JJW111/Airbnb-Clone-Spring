package com.clone.airbnb.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.entity.Review;

@EntityMapping(entity=Review.class)
public interface ReviewRepository extends PagingAndSortingRepository<Review, Integer> {
}

