package com.clone.airbnb.admin.schema.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Entities {
	private final Map<String, Entity> map;
	
	public Entity get(String entityName) {
		return this.map.get(entityName);
	}
	
	public Set<String> keySet() {
		return this.map.keySet();
	}
	
	public static class Builder {
		private final Map<String, Entity> map = new HashMap<>();
		
		public Builder() {}
		
		public Builder put(String entityName, Entity entity) {
			this.map.put(entityName, entity);
			return this;
		}
		
		public Entities build() {
			return new Entities(this);
		}
	}
	
	public Entities(Builder builder) {
		this.map = builder.map;
	}
}
