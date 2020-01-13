package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class DefaultInfo implements FormInfo {
	private final String formName;
	private final boolean blank;
	
	public DefaultInfo(String formName, boolean blank) {
		this.formName = formName;
		this.blank = blank;
	}
}
