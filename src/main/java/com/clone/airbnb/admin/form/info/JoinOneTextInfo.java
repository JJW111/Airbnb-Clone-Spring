package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class JoinOneTextInfo extends PlaceholderTextInfo {
	private final String field;
	
	public JoinOneTextInfo(boolean blank, int maxlength, String placeholder, String field) {
		super(blank, maxlength, placeholder);
		this.field = field;
	}
}
