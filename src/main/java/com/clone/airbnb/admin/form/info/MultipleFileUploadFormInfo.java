package com.clone.airbnb.admin.form.info;

import com.clone.airbnb.admin.form.annotation.Accept;

import lombok.Getter;


@Getter
public class MultipleFileUploadFormInfo extends FielUploadDefaultInfo {
	public MultipleFileUploadFormInfo(String fileFormName, boolean blank, String labelText, Accept accept) {
		super(fileFormName, blank, labelText, accept);
	}
}
