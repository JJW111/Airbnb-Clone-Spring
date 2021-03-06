package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class IntegerInfo extends PlaceholderTextInfo {
	private final int min;
	private final int max;
	
	public IntegerInfo(boolean blank, int maxlength, String placeholder, int min, int max) {
		super(blank, maxlength, placeholder);
		this.max = max;
		this.min = min;
	}
}
