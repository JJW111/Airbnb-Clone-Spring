package com.clone.airbnb.entity.projection.admin;

import java.util.List;

import com.clone.airbnb.entity.projection.SelectRoom;
import com.clone.airbnb.entity.projection.SelectUser;

public interface WatchlistProjection {
	Integer getId();
	String getName();
	List<SelectUser> getUsers();
	List<SelectRoom> getRooms();
	
	default int countUsers() {
		if (getUsers() == null) return 0;
		return getUsers().size();
	}
	
	default int countRooms() {
		if (getRooms() == null) return 0;
		return getRooms().size();
	}
}
