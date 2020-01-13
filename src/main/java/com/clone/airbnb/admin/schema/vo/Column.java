package com.clone.airbnb.admin.schema.vo;

import java.lang.reflect.Field;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class Column {
	private final String name;
	private final FormType formType;
	private final Class<?> typeClass;
	
	public Column(Field f, Class<Object> entityClass) {
		this.formType = FormType.getType(f, entityClass);
		
		if (this.formType != null) {
			this.name = f.getName();
			this.typeClass = f.getType();
		} else {
			this.name = null;
			this.typeClass = null;
		}
	}
}
