package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class JoinManyInfo extends JoinOneInfo {
	public JoinManyInfo(boolean blank, String defaultOption, 
			String itemLabel, String itemValue, String method) {
		super(blank, defaultOption, itemLabel, itemValue, method);
	}
}
