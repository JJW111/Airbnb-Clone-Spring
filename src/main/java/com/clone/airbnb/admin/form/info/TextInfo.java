package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class TextInfo extends DefaultInfo {
	private final int maxlength;
	
	public TextInfo(String formName, boolean blank, int maxlength) {
		super(formName, blank);
		this.maxlength = maxlength;
	}
}
