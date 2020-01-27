package com.clone.airbnb.entity.projection.admin;

import java.util.Date;

import com.clone.airbnb.entity.enu.ReservationStatus;
import com.clone.airbnb.entity.projection.SelectRoom;
import com.clone.airbnb.entity.projection.SelectUser;

public interface ReservationProjection {
	Integer 			getId();
	ReservationStatus 	getStatus();
	Date				getCheckIn();
	Date				getCheckOut();
	SelectUser			getGuest();
	SelectRoom			getRoom();
}
