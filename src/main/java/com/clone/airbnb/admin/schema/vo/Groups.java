package com.clone.airbnb.admin.schema.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>그룹 이름을 Key, {@link Group} 객체를 값 형태로 관리한다.
 * 
 * @author jjw
 *
 */
public class Groups {
	private final Map<String, Group> map;

	public Group get(String groupName) {
		return this.map.get(groupName);
	}
	
	public Set<String> keySet() {
		return this.map.keySet();
	}
	
	public static class Builder {
		private final Map<String, Group> map = new HashMap<>();
		
		private Map<String, Group.Builder> builderMap = new HashMap<>();
		
		
		
		public Builder() {}
		
		
		
		public void put(String groupName, String entityName) {
			this.put(groupName, new Name(entityName));
		}
		
		
		
		public void put(String groupName, Name name) {
			Group.Builder builder = null;
			
			if (this.builderMap.containsKey(groupName)) {
				builder = this.builderMap.get(groupName);
			} else {
				builder = new Group.Builder(groupName);
			}
			
			builder.add(name);
			
			this.builderMap.put(groupName, builder);
		}
		
		
		
		public Groups build() {
			for (String gruopName : this.builderMap.keySet()) {
				Group.Builder builder = this.builderMap.get(gruopName);
				Group group = builder.build();
				
				this.map.put(gruopName, group);
			}
			
			return new Groups(this);
		}
	}
	
	private Groups(Builder builder) {
		this.map = builder.map;
	}
}
