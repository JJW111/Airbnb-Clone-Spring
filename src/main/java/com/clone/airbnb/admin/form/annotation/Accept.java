package com.clone.airbnb.admin.form.annotation;

public enum Accept {
	ALL("*"),
	ALL_VIDIO("video/*"),
	ALL_AUDIO("audio/*"),
	ALL_IMAGE("image/*");
	
	private final String value;
	
	private Accept(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
