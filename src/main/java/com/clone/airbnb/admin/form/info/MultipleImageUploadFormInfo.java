package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class MultipleImageUploadFormInfo extends ImageUploadFormInfo {
	public MultipleImageUploadFormInfo(String formName, boolean blank, String labelText) {
		super(formName, blank, labelText);
	}
}
