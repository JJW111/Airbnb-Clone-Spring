package com.clone.airbnb.admin.form.info;

import com.clone.airbnb.admin.form.annotation.Accept;

import lombok.Getter;

@Getter
public class FielUploadDefaultInfo extends DefaultInfo {
	private final String labelText;
	private final String accept;
	
	public FielUploadDefaultInfo(String formName, boolean blank, String labelText, Accept accept) {
		super(formName, blank);
		this.labelText = labelText;
		this.accept = accept.value();
	}
}
