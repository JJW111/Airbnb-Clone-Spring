package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class TextInfo extends DefaultInfo {
	private final int maxlength;
	
	public TextInfo(boolean blank, int maxlength) {
		super(blank);
		this.maxlength = maxlength;
	}
}
