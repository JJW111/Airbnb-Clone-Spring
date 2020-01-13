package com.clone.airbnb.admin.schema.vo;

import com.clone.airbnb.utils.WordUtils;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Name {
	private final String origin;
	private final String alias;
	
	public Name(String origin, String alias) {
		this.origin = origin;
		this.alias = alias;
	}
	
	public Name(String origin) {
		this(origin, WordUtils.toAlias(origin));
	}
}
