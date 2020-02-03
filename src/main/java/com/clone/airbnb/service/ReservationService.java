package com.clone.airbnb.service;

import java.util.Date;
import java.util.List;

import com.clone.airbnb.entity.Reservation;

public interface ReservationService {
	List<Reservation> listOfHost(String username);
	List<Reservation> listOfGuest(String username);
	Reservation reserve(String username, Integer roomId, Date checkIn, Date checkOut);
	Reservation get(Integer id);
	void cancel(Integer id);
	void confirm(Integer id);
}
