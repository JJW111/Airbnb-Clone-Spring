package com.clone.airbnb.admin.form.info;

import java.util.Map;

import lombok.Getter;

@Getter
public class MapSelectBoxInfo extends DefaultInfo {
	private final String defaultOption;
	private final Map<String, String> values;
	
	public MapSelectBoxInfo(String formName, boolean blank, String defaultOption, Map<String, String> values) {
		super(formName, blank);
		this.defaultOption = defaultOption;
		this.values = values;
	}
	
}
