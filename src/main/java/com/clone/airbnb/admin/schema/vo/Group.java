package com.clone.airbnb.admin.schema.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * <p>어드민 페이지에서 사용하는 그룹에 관한 정보를 가지고 있다.
 * 
 * @author jjw
 *
 */
public class Group {
	@Getter
	private final String name;
	private final List<Name> entityNameList;
	
	public Name get(int index) {
		return this.entityNameList.get(index);
	}
	
	public int size() {
		return this.entityNameList.size();
	}
	
	public int endIndex() {
		return this.size() - 1;
	}
	
	public static class Builder {
		private final String name;
		private final List<Name> entityNameList = new ArrayList<>();
		
		public Builder(String name) {
			this.name = name;
		}
		
		public Builder add(String entityName) {
			this.entityNameList.add(new Name(entityName));
			return this;
		}
		
		public Builder add(Name entityName) {
			this.entityNameList.add(entityName);
			return this;
		}
		
		public Group build() {
			return new Group(this);
		}
	}
	
	private Group(Builder builder) {
		this.name = builder.name;
		this.entityNameList = builder.entityNameList;
	}
}
