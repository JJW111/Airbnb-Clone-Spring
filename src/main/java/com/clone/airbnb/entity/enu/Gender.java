package com.clone.airbnb.entity.enu;

import lombok.Getter;

@Getter
public enum Gender {
	MALE("Male"),
	FEMALE("Female"),
	OTHER("Other");
	
	private String value;
	
	private Gender(String value) {
		this.value = value;
	}
	
	public static String value(String name) {
		Gender g = Gender.valueOf(name);
		return g.getValue();
	}
	
}
