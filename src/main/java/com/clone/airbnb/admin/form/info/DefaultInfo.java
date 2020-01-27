package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class DefaultInfo implements FormInfo {
	private final boolean blank;
	
	public DefaultInfo(boolean blank) {
		this.blank = blank;
	}
}
