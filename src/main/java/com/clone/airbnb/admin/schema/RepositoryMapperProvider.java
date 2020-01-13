package com.clone.airbnb.admin.schema;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.clone.airbnb.admin.context.ApplicationContextProvider;
import com.clone.airbnb.admin.exception.ApplicationContextBeanCreationException;
import com.clone.airbnb.admin.schema.vo.RepositoryMapper;
import com.clone.airbnb.annotation.EntityMapping;



public class RepositoryMapperProvider {
	
	private final List<Class<PagingAndSortingRepository<Object, Integer>>> classes;
	
	private RepositoryMapper repositoryMapper;
	

	
	
	public RepositoryMapperProvider(final List<Class<PagingAndSortingRepository<Object, Integer>>> classes) {
		this.classes = classes;
	}
	
	
	
	public RepositoryMapper getRepositoryMapper() {
		if (this.repositoryMapper == null) {
			synchronized (this) {
				if (this.repositoryMapper == null) {
					this.repositoryMapper = this.create();
				}
			}
		}
		
		return this.repositoryMapper;
	}
	
	
	

	@SuppressWarnings("unchecked")
	private RepositoryMapper create() {
		if (this.classes == null || this.classes.size() < 1) {
			throw new RuntimeException("Repository 클래스가 로드되지 않았습니다.");
		};
		
		if (ApplicationContextProvider.getApplicationContext() == null) {
			throw new ApplicationContextBeanCreationException("ApplicationContext 객체가 null 입니다. " + ApplicationContextProvider.class.getName() + " 객체를 생성하여 빈으로 등록하십시오. ");
		}

		RepositoryMapper.Builder builder = new RepositoryMapper.Builder();
		
		for (int i = 0; i < this.classes.size(); i++) {

			Class<?> cls = this.classes.get(i);
            
            EntityMapping entityMapping = cls.getAnnotation(EntityMapping.class);

            builder.put(entityMapping.entity().getSimpleName(), (PagingAndSortingRepository<Object, Integer>) ApplicationContextProvider.getApplicationContext().getBean(cls));
		}
		
		return builder.build();
	}
	
}
