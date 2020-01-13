package com.clone.airbnb.admin.schema;

import java.util.List;

import com.clone.airbnb.admin.annotation.AdminEntityDefinition;
import com.clone.airbnb.admin.exception.AdminEntityFailToLoadClassException;
import com.clone.airbnb.admin.schema.vo.AdminDefinitionObject;
import com.clone.airbnb.admin.schema.vo.AdminEntity;
import com.clone.airbnb.admin.schema.vo.Groups;
import com.clone.airbnb.admin.schema.vo.Name;
import com.clone.airbnb.admin.exception.AdminEntityFailToInstantiateException;



/**
 * <p>{@link AdminEntity} 객체를 생성한다.
 * 
 * @author jjw
 *
 */
public class AdminEntityProvider {
	
	private final List<Class<AdminEntityConfiguration>> classes;
	
	private AdminDefinitionObject adminDefinitionObject;
	
	
	public AdminEntityProvider(List<Class<AdminEntityConfiguration>> classes) {
		this.classes = classes;
	}
	
	
	public AdminDefinitionObject getAdminDefinitionObject() {
		if (this.adminDefinitionObject == null) {
			synchronized (this) {
				if (this.adminDefinitionObject == null) {
					this.adminDefinitionObject = this.create();
				}
			}
		}
		
		return this.adminDefinitionObject;
	}
	
	
	
	
	private AdminDefinitionObject create() {
		if (this.classes == null || this.classes.size() < 1) {
			throw new AdminEntityFailToLoadClassException("AdminEntityConfiguration 클래스가 로드되지 않았습니다.");
		};
		
		AdminEntity.Builder adminEntitiesBuilder = new AdminEntity.Builder();
		
		Groups.Builder groupsBuilder = new Groups.Builder();
		
		try {
			for (int i = 0; i < this.classes.size(); i++) {
				Class<AdminEntityConfiguration> cl = this.classes.get(i); 
				
				AdminEntityConfiguration configuration = (AdminEntityConfiguration) cl.newInstance();
				
				AdminEntityDefinition definition = cl.getAnnotation(AdminEntityDefinition.class);
				
				Class<?> entity = definition.entity();
				
				adminEntitiesBuilder.put(entity.getSimpleName(), configuration);
				
				groupsBuilder.put(configuration.group(), new Name(entity.getSimpleName()));
			}
		} catch (Exception e) {
			throw new AdminEntityFailToInstantiateException("AdminEntityConfiguration 인터페이스를 객체화 하는데 실패했습니다.", e);
		}
		
		return new AdminDefinitionObject(adminEntitiesBuilder.build(), groupsBuilder.build());
	}
	
}
