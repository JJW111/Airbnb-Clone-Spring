package com.clone.airbnb.admin.schema.vo;

import java.util.List;

import com.clone.airbnb.utils.WordUtils;

import lombok.Getter;

@Getter
public class Entity {
	private final Name name;
	private final boolean isUserEntity;
	private final Columns columns;
	private final List<String> methods;
	private final Class<Object> entityClass;

	
	public Entity(String name, Columns columns, List<String> methods, Class<Object> entityClass, boolean isUserEntity) {
		this(name, WordUtils.toAlias(name), columns, methods, entityClass, isUserEntity);
	}
	
	public Entity(String name, String alias, Columns columns, List<String> methods, Class<Object> entityClass, boolean isUserEntity) {
		this.name = new Name(name, alias);
		this.columns = columns;
		this.entityClass = entityClass;
		this.isUserEntity = isUserEntity;
		this.methods = methods;
	}
}
