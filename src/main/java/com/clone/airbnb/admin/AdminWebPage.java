package com.clone.airbnb.admin;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.context.request.WebRequest;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.AdminEntityProvider;
import com.clone.airbnb.admin.schema.EntityProvider;
import com.clone.airbnb.admin.schema.RepositoryMapperProvider;
import com.clone.airbnb.admin.schema.vo.AdminEntity;
import com.clone.airbnb.utils.ReflectionInvocator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AdminWebPage {
	private final AdminEntityProvider adminEntityProvider;
	private final EntityProvider entityProvider;
	private final RepositoryMapperProvider repositoryMapperProvider;
	
	
	

	public Page<Object> findAll(String entityName, Pageable pageable) {
		return (Page<Object>) repositoryMapperProvider.getRepositoryMapper().get(entityName).findAll(pageable);
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public Page<Object> findAllBy(String entityName, Pageable pageable, Class<?> clazz) {
		return (Page<Object>) this.find(entityName, "findAllBy", new Class<?>[] { Pageable.class, Class.class }, pageable, clazz);
	}
	
	
	
	public Object findById(String entityName, Integer id) {
		Optional<Object> opt = repositoryMapperProvider.getRepositoryMapper().get(entityName).findById(id);
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
	
	
	
	public Object findById(String entityName, Integer id, Class<?> clazz) {
		@SuppressWarnings("unchecked")
		Optional<Object> opt = (Optional<Object>) this.find(entityName, "findById", new Class<?>[] { Integer.class, Class.class }, id, clazz);
		
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
	
	
	
	
	public Object find(String entityName, String methodName, Class<?>[] parameterTypes, Object... args) {
		return ReflectionInvocator.invoke(repositoryMapperProvider.getRepositoryMapper().get(entityName), methodName, parameterTypes, args);
	}
	
	
	
	
	public BindingResult bind(String entityName, Object entityObj, WebRequest webRequest) {
		AdminEntity adminEntity = adminEntityProvider.getAdminDefinitionObject().getAdminEntity();
		AdminEntityConfiguration config = adminEntity.get(entityName);

		WebRequestDataBinder binder = new WebRequestDataBinder(entityObj);
		
		config.initWebRequestDataBinder(binder);
		
		binder.bind(webRequest);
		binder.validate();

		return binder.getBindingResult();
	}
	
	
	
	
	
	
	public Object save(String entityName, Object entityObj) {
		ReflectionInvocator.invoke(entityObj, "beforeCreate");
		Object obj = repositoryMapperProvider.getRepositoryMapper().get(entityName).save(entityObj);
		return obj;
	}
	
	
	
	
	
	
	public void delete(String entityName, Integer id) {
		Object entityObj = findById(entityName, id);
		ReflectionInvocator.invoke(entityObj, "beforeDelete");
		repositoryMapperProvider.getRepositoryMapper().get(entityName).deleteById(id);
	}
	
	
	
	
	
	public void update(String entityName, Object newObj, Integer id) {
		PagingAndSortingRepository<Object, Integer> pagingAndSortingRepository = repositoryMapperProvider.getRepositoryMapper().get(entityName);
		
		Object old = pagingAndSortingRepository.findById(id).get();

		ReflectionInvocator.invoke(newObj, "beforeUpdate", Object.class, old);
		ReflectionInvocator.invoke(old, "override", Object.class, newObj);
		
		pagingAndSortingRepository.save(old);
	}
	
}
