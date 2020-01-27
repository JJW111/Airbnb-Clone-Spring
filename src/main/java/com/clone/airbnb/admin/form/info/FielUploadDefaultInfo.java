package com.clone.airbnb.admin.form.info;

import com.clone.airbnb.admin.form.annotation.Accept;

import lombok.Getter;

@Getter
public class FielUploadDefaultInfo extends DefaultInfo {
	private final String fileFormName;
	private final String labelText;
	private final String accept;
	
	public FielUploadDefaultInfo(String fileFormName, boolean blank, String labelText, Accept accept) {
		super(blank);
		this.fileFormName = fileFormName;
		this.labelText = labelText;
		this.accept = accept.value();
	}
}
