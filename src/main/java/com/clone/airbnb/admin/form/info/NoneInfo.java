package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class NoneInfo implements FormInfo {
	private final String typeName;
	
	public NoneInfo(Class<?> fieldType) {
		this.typeName = fieldType.getSimpleName();
	}
}
