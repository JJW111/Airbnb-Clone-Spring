package com.clone.airbnb.admin.form.info;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.clone.airbnb.admin.context.ApplicationContextProvider;
import com.clone.airbnb.utils.ReflectionInvocator;

import lombok.Getter;

@Getter
public class JoinOneInfo extends DefaultInfo {
	private final String field;
	private final String defaultOption;
	private final Class<?> repositoryClass;
	private final String findAllMethod;
	private PagingAndSortingRepository<Object, Integer> repository;
	
	public JoinOneInfo(String formName, boolean blank, String field, String defaultOption, Class<?> repositoryClass, String findAllMethod) {
		super(formName, blank);
		this.field = field;
		this.defaultOption = defaultOption;
		this.repositoryClass = repositoryClass;
		this.findAllMethod = findAllMethod;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Map<Integer, Object> getValues() {
		if (this.repository == null) {
			this.repository = (PagingAndSortingRepository<Object, Integer>) ApplicationContextProvider
					.getApplicationContext().getBean(this.repositoryClass);
		}
		
		Iterable<Object> iter = null;
		
		if (this.findAllMethod.trim().isEmpty()) {
			iter = this.repository.findAll();
		} else {
			iter = (Iterable<Object>) ReflectionInvocator.invoke(this.repository, findAllMethod);
		}
		
		if (iter == null) {
			return null;
		} else {
			LinkedHashMap<Integer, Object> map = new LinkedHashMap<>();
			
			for (Object o : iter) {
				Integer id = (Integer) ReflectionInvocator.get(o, "id");
				Object value = ReflectionInvocator.get(o, field);
				map.put(id, value);
			}
			
			return map;
		}

	}
	
}
