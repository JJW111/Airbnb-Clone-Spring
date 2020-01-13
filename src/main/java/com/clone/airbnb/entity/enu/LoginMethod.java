package com.clone.airbnb.entity.enu;

import lombok.Getter;

@Getter
public enum LoginMethod {
	EMAIL("Email"),
	GITHUB("Github"),
	KAKAO("Kakao");
	
	private String value;
	
	private LoginMethod(String value) {
		this.value = value;
	}
	
	public static String value(String name) {
		LoginMethod c = LoginMethod.valueOf(name);
		return c.getValue();
	}
	
}
