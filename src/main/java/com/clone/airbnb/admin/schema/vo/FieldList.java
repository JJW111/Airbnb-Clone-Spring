package com.clone.airbnb.admin.schema.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.clone.airbnb.utils.WordUtils;

import lombok.ToString;

@ToString
public class FieldList {
	
	private Map<String, String> map;
	
	
	
	public String get(final String key) { 
		return this.map.get(key);
	}
	
	
	
	public Set<String> keySet() {
		return this.map.keySet();
	}
	
	
	
	public Collection<String> values() {
		return this.map.values();
	}
	
	
	
	public static class Builder {
		
		private Map<String, String> map = new LinkedHashMap<>();
		
		
		
		public Builder field(String key) {
			map.put(key, WordUtils.toAlias(key));
			return this;
		}
		
		
		
		public Builder field(String key, String value) {
			map.put(key, value);
			return this;
		}
		
		
		
		public FieldList build() {
			return new FieldList(this);
		}
		
	}
	
	
	
	public static Builder builder() {
		return new Builder();
	}
	
	
	
	private FieldList(Builder builder) {
		this.map = builder.map;
	}
	
}
