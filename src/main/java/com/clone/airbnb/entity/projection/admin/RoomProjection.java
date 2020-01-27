package com.clone.airbnb.entity.projection.admin;

import java.util.Date;
import java.util.List;

import com.clone.airbnb.admin.entity.FileProjectionFrame;
import com.clone.airbnb.entity.Room;
import com.clone.airbnb.entity.projection.ReviewRating;
import com.clone.airbnb.entity.projection.SelectItem;
import com.clone.airbnb.entity.projection.SelectUser;

public interface RoomProjection {
	Integer getId();
	String getName();
	String getDescription();
	String getAddress();
	String getCity();
	String getCountry();
	Integer getPrice();
	Integer getGuests();
	Integer getBeds();
	Integer getBedrooms();
	Integer getBaths();
	Date getCheckIn();
	Date getCheckOut();
	Boolean getInstantBook();
	SelectUser getHost();
	SelectItem getRoomType();
	List<SelectItem> getFacilities();
	List<SelectItem> getAmenities();
	List<SelectItem> getHouseRules();
	List<FileProjectionFrame> getPhotos();
	List<ReviewRating> getReviews();

	default double totalRating() {
		return Room.totalRating(this.getReviews());
	}
}
