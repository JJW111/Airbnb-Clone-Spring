package com.clone.airbnb.entity.enu;

import lombok.Getter;

@Getter
public enum Language {
	ENGLISH("English"),
	KOREAN("Korean");
	
	private String value;
	
	private Language(String value) {
		this.value = value;
	}
	
	public static String value(String name) {
		Language l = Language.valueOf(name);
		return l.getValue();
	}

}
