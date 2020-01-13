package com.clone.airbnb.admin.form.info;

import com.clone.airbnb.admin.form.annotation.Accept;

import lombok.Getter;


@Getter
public class MultipleFileUploadFormInfo extends FielUploadDefaultInfo {
	public MultipleFileUploadFormInfo(String formName, boolean blank, String labelText, Accept accept) {
		super(formName, blank, labelText, accept);
	}
}
