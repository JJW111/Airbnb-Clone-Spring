package com.clone.airbnb.admin.schema.vo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.repository.PagingAndSortingRepository;




/**
 * <p>Entity 이름을 키, {@link RepositoryWrapper} 객체를 값으로 매핑한다.
 * 
 * @author jjw
 *
 */
public class RepositoryMapper {
	
	private final Map<String, PagingAndSortingRepository<Object, Integer>> map;
	
	
	
	public PagingAndSortingRepository<Object, Integer> get(final String entityName) {
		return this.map.get(entityName);
	}
	
	
	
	public static class Builder {
		private final Map<String, PagingAndSortingRepository<Object, Integer>> map = new HashMap<>();
		
		
		
		public void put(String entityName, PagingAndSortingRepository<Object, Integer> repository) {
			this.map.put(entityName, repository);
		}
		
		
		
		public RepositoryMapper build() {
			return new RepositoryMapper(this);
		}
	}
	
	
	
	
	private RepositoryMapper(Builder builder) {
		this.map = builder.map;
	}
}
