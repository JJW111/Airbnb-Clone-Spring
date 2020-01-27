package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class MultipleImageUploadFormInfo extends ImageUploadFormInfo {
	public MultipleImageUploadFormInfo(String fileFormName, boolean blank, String labelText) {
		super(fileFormName, blank, labelText);
	}
}
