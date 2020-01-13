package com.clone.airbnb.admin.schema.vo;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import lombok.ToString;



/**
 * 관리자 페이지 {@code Entity} 상세보기 페이지에 사용하는 필드 정보를 설정하는 클래스
 * 
 * @author jjw
 */
@ToString
public class FieldSet {
	
	private final Map<String, FieldList> fieldSet;
	
	
	
	public FieldList get(String setName) {
		return this.fieldSet.get(setName);
	}
	
	
	
	public Set<String> keySet() {
		return this.fieldSet.keySet();
	}
	
	
	
	
	public static class Builder {
		
		private String setName = null;
		
		private final Map<String, FieldList.Builder> fieldSet = new LinkedHashMap<>();
		
		
		
		public Builder() {}
		
		
		

		public Builder set(String setName) {
			this.setName = setName;
			return this;
		}
		
		
		
		
		public Builder field(String key) {
			FieldList.Builder builder = this.getFieldList(setName);
			if (builder == null) return null;
			builder.field(key);
			this.fieldSet.put(setName, builder);
			return this;
		}
		
		
		
		
		
		
		public Builder field(String key, String value) {
			FieldList.Builder builder = this.getFieldList(setName);
			if (builder == null) return null;
			builder.field(key, value);
			this.fieldSet.put(setName, builder);
			return this;
		}
		
		
		
		
		
		private FieldList.Builder getFieldList(String setName) {
			if (this.setName == null) return null;
			
			FieldList.Builder builder = this.fieldSet.get(setName);
			
			if (builder == null) {
				builder = FieldList.builder();
			}
			
			return builder;
		}
		
		
		
		
		
		
		public FieldSet build() {
			return new FieldSet(this);
		}
		
	}
	
	
	
	private FieldSet(Builder builder) {
		Map<String, FieldList> map = new LinkedHashMap<>();
		
		if (!builder.fieldSet.isEmpty()) {
			for (String key : builder.fieldSet.keySet()) {
				map.put(key, builder.fieldSet.get(key).build());
			}
		}
		
		this.fieldSet = map;
	}
	
}
