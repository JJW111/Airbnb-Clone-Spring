package com.clone.airbnb.admin.schema.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import lombok.ToString;

@ToString
public class Columns {
	private final Map<String, Column> map;
	private boolean hasFileUploadForm = false;
	
	
	public Column get(String columnName) {
		return this.map.get(columnName);
	}
	
	
	
	public Set<String> keySet() {
		return this.map.keySet();
	}
	
	
	
	public Collection<Column> values() {
		return this.map.values();
	}
	
	
	
	public boolean containsKey(String key) {
		return this.map.containsKey(key);
	}
	
	
	
	public boolean hasFileUploadForm() {
		return this.hasFileUploadForm;
	}
	
	
	
	public static class Builder {
		
		private final Map<String, Column> map = new LinkedHashMap<>();
		

		
		public Builder put(String columnName, Column column) {
			this.map.put(columnName, column);
			return this;
		}
		
		
		
		public Columns build() {
			return new Columns(this);
		}
		
	}
	
	
	
	public static Builder builder() {
		return new Builder();
	}
	
	
	
	private Columns(Builder builder) {
		this.map = builder.map;
		
		for (Column c: values()) {
			FieldType t = c.getFormType().getFieldType();
			
			if (t == FieldType.FILE_UPLOAD_FORM || t == FieldType.IMAGE_UPLOAD_FORM 
					|| t == FieldType.MULTIPLE_FILE_UPLOAD_FORM || t == FieldType.MULTIPLE_IMAGE_UPLOAD_FORM) {
				this.hasFileUploadForm = true;
				break;
			}
		}
	}
}
