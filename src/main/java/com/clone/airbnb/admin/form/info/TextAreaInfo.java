package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class TextAreaInfo extends PlaceholderTextInfo {
	private final int rows;

	public TextAreaInfo(boolean blank, int maxlength, String placeholder, int rows) {
		super(blank, maxlength, placeholder);
		this.rows = rows;
	}
}
