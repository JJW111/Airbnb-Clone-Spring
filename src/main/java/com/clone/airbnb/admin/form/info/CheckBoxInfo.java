package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class CheckBoxInfo extends DefaultInfo {
	public CheckBoxInfo(String formName) {
		super(formName, true);
	}
}
