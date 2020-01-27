package com.clone.airbnb.entity.projection;

import java.util.Date;
import java.util.List;

public interface RoomUpdate {
	Integer 	getId();
	String 		getName();
	String 		getDescription();
	String 		getAddress();
	String 		getCity();
	String 		getCountry();
	Integer 	getPrice();
	Integer 	getGuests();
	Integer 	getBeds();
	Integer 	getBedrooms();
	Integer 	getBaths();
	Date 		getCheckIn();
	Date 		getCheckOut();
	Boolean 	getInstantBook();
	SelectItem 	getRoomType();
	List<SelectItem>	getFacilities();
	List<SelectItem> 	getAmenities();
	List<SelectItem> 	getHouseRules();
}
