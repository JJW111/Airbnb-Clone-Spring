package com.clone.airbnb.entity.projection.admin;

import com.clone.airbnb.entity.projection.SelectRoom;
import com.clone.airbnb.entity.projection.SelectUser;

public interface ReviewProjection {
	Integer getId();
	String getReview();
	Integer getAccuracy();
	Integer getCommunication();
	Integer getCleaniness();
	Integer getLocation();
	Integer getCheckIn();
	Integer getValue();
	SelectUser getUser();
	SelectRoom getRoom();
}
