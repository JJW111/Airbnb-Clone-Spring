package com.clone.airbnb.admin.form.info;

import lombok.Getter;

@Getter
public class JoinManyInfo extends JoinOneInfo {
	public JoinManyInfo(String formName, boolean blank, String field, String defaultOption, Class<?> repositoryClass, String findAllMethod) {
		super(formName, blank, field, defaultOption, repositoryClass, findAllMethod);
	}
}
