package com.clone.airbnb.entity.projection;

import java.util.Date;
import java.util.List;

import com.clone.airbnb.entity.Room;

public interface RoomDetail {
	Integer 			getId();
	String 				getName();
	String 				getDescription();
	String 				getCity();
	Integer 			getPrice();
	Integer 			getGuests();
	Integer 			getBeds();
	Integer 			getBedrooms();
	Integer 			getBaths();
	Date 				getCheckIn();
	Date 				getCheckOut();
	Boolean 			getInstantBook();
	DetailHost 			getHost();
	Item 				getRoomType();
	List<Item> 			getFacilities();
	List<Item> 			getAmenities();
	List<Item> 			getHouseRules();
	List<DetailReview> 	getReviews();
	List<PhotoOnlyPath> 		getPhotos();
	
	default double totalRating() {
		return Room.totalRating(this.getReviews());
	}
}
