package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class SelectBoxInfo extends DefaultInfo {
	private final String defaultOption;
	private final Object[] values;
	
	public SelectBoxInfo(String formName, boolean blank, String defaultOption, Object[] values) {
		super(formName, blank);
		this.defaultOption = defaultOption;
		this.values = values;
	}
	
}
