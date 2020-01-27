package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class MapSelectBoxInfo extends DefaultInfo {
	private final String defaultOption;
	private final String method;
	
	public MapSelectBoxInfo(boolean blank, String defaultOption, String method) {
		super(blank);
		this.defaultOption = defaultOption;
		this.method = method;
	}
	
}
