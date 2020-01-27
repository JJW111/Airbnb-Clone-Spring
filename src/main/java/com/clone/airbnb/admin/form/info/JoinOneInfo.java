package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class JoinOneInfo extends DefaultInfo {
	private final String defaultOption;
	private final String itemLabel;
	private final String itemValue;
	private final String method;
	
	
	public JoinOneInfo(boolean blank, String defaultOption, 
			String itemLabel, String itemValue, String method) {
		super(blank);
		this.defaultOption = defaultOption;
		this.itemLabel = itemLabel;
		this.itemValue = itemValue;
		this.method = method;
	}
	
}
