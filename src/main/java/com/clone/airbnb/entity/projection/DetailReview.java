package com.clone.airbnb.entity.projection;

import java.util.Date;

import com.clone.airbnb.entity.Review;
import com.clone.airbnb.entity.sup.RatingAverageReview;

public interface DetailReview extends RatingAverageReview {
	String 	getReview();
	Integer getAccuracy();
	Integer getCommunication();
	Integer getCleaniness();
	Integer getLocation();
	Integer getCheckIn();
	Integer getValue();
	DetailReviewUser getUser();
	Date	getCreated();
	
	default double ratingAverage() {
		return Review.ratingAverage(this.getAccuracy(), this.getCommunication(), this.getCleaniness()
				, this.getLocation(), this.getCheckIn(), this.getValue());
	}
}
