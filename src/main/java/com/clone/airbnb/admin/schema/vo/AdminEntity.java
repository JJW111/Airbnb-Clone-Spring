package com.clone.airbnb.admin.schema.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.clone.airbnb.admin.schema.AdminEntityConfiguration;

/**
 * 어드민 페이지에 관한 정보를 담고있다.
 * 
 * @author jjw
 *
 */
public class AdminEntity {
	private final Map<String, AdminEntityConfiguration> map;
	
	public AdminEntityConfiguration get(String entityName) {
		return this.map.get(entityName);
	}
	
	public Set<String> keySet() {
		return this.map.keySet();
	}
	
	public static class Builder {
		private final Map<String, AdminEntityConfiguration> map = new HashMap<>();
		
		public Builder() {}
		
		public Builder put(String entityName, AdminEntityConfiguration config) {
			this.map.put(entityName, config);
			return this;
		}
		
		public AdminEntity build() {
			return new AdminEntity(this);
		}
	}
	
	private AdminEntity(Builder builder) {
		this.map = builder.map;
	}
}
