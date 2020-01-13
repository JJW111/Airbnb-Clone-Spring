package com.clone.airbnb.entity.enu;

import lombok.Getter;

@Getter
public enum Currency {
	USD("USD"),
	KRW("KRW");
	
	private String value;
	
	private Currency(String value) {
		this.value = value;
	}
	
	public static String value(String name) {
		Currency c = Currency.valueOf(name);
		return c.getValue();
	}
	
}
