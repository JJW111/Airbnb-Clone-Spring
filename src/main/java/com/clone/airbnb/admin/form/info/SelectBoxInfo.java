package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class SelectBoxInfo extends DefaultInfo {
	private final String defaultOption;
	
	public SelectBoxInfo(boolean blank, String defaultOption) {
		super(blank);
		this.defaultOption = defaultOption;
	}
	
}
