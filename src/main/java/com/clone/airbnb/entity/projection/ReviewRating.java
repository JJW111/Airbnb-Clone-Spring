package com.clone.airbnb.entity.projection;

import com.clone.airbnb.entity.Review;
import com.clone.airbnb.entity.sup.RatingAverageReview;

public interface ReviewRating extends RatingAverageReview {
	Integer getAccuracy();
	Integer getCommunication();
	Integer getCleaniness();
	Integer getLocation();
	Integer getCheckIn();
	Integer getValue();
	
	default double ratingAverage() {
		return Review.ratingAverage(this.getAccuracy(), this.getCommunication(), this.getCleaniness()
				, this.getLocation(), this.getCheckIn(), this.getValue());
	}
}
