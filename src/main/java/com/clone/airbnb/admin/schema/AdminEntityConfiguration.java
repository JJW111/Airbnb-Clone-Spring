package com.clone.airbnb.admin.schema;

import org.springframework.web.bind.support.WebRequestDataBinder;

import com.clone.airbnb.admin.schema.vo.FieldList;
import com.clone.airbnb.admin.schema.vo.FieldSet;

public interface AdminEntityConfiguration {
	String group();
	FieldList fieldList();
	FieldSet fieldSet();
	void initWebRequestDataBinder(WebRequestDataBinder binder);
}
