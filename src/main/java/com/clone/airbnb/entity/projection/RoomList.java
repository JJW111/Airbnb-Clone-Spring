package com.clone.airbnb.entity.projection;

import java.util.List;

import com.clone.airbnb.entity.Room;

public interface RoomList {
	Integer 			getId();
	String				getName();
	String 				getCity();
	String 				getCountry();
	HostOnlySuperhost 	getHost();
	List<PhotoOnlyPath> getPhotos();
	List<ReviewRating> 	getReviews();
	
	default Double totalRating() {
		return Room.totalRating(this.getReviews());
	}
}
