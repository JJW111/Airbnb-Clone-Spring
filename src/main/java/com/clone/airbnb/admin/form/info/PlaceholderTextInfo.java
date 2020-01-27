package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class PlaceholderTextInfo extends TextInfo {
	private final String placeholder;
	
	public PlaceholderTextInfo(boolean blank, int maxlength, String placeholder) {
		super(blank, maxlength);
		this.placeholder = placeholder;
	}
}
