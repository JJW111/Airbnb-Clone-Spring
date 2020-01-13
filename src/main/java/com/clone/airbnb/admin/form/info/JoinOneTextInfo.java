package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class JoinOneTextInfo extends PlaceholderTextInfo {
	private final String field;
	
	public JoinOneTextInfo(String formName, boolean blank, int maxlength, String placeholder, String field) {
		super(formName, blank, maxlength, placeholder);
		this.field = field;
	}
}
