package com.clone.airbnb.service;

import com.clone.airbnb.entity.Review;

public interface ReviewService {
	boolean isValid(Integer reservationId, String username);
	void write(Review review, Integer reservationid);
}
