package com.clone.airbnb.entity.enu;

import lombok.Getter;

@Getter
public enum ReservationStatus {
	PENDING("pending"),
	COMFIRMED("confirmed"),
	CANCELED("canceled");
	
	private String value;
	
	private ReservationStatus(String value) {
		this.value = value;
	}
	
	public static String value(String name) {
		ReservationStatus c = ReservationStatus.valueOf(name);
		return c.getValue();
	}
	
}
