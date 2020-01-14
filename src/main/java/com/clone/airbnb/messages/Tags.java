package com.clone.airbnb.messages;

import lombok.Getter;

public enum Tags {
	DEBUG("debug"),
	INFO("info"),
	SUCCESS("success"),
	WARNING("warning"),
	ERROR("error");
	
	@Getter
	private String className;
	
	private Tags(String className) {
		this.className = className;
	}
	
	@Override
	public String toString() {
		return this.className;
	}
	
}
