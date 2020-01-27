package com.clone.airbnb.admin.form.info;

import com.clone.airbnb.admin.form.annotation.Accept;

import lombok.Getter;

@Getter
public class ImageUploadFormInfo extends FielUploadDefaultInfo {
	public ImageUploadFormInfo(String fileFormName, boolean blank, String labelText) {
		super(fileFormName, blank, labelText, Accept.ALL_IMAGE);
	}
}
