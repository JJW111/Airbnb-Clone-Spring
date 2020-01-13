package com.clone.airbnb.admin.schema;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.clone.airbnb.admin.context.ApplicationContextProvider;
import com.clone.airbnb.admin.entity.AdminFormEntity;
import com.clone.airbnb.admin.exception.ApplicationContextBeanCreationException;
import com.clone.airbnb.admin.exception.EntityFailToLoadClassException;
import com.clone.airbnb.admin.exception.EntitySchemaCreationException;
import com.clone.airbnb.admin.exception.EntitySchemaInvalidEntityClassException;
import com.clone.airbnb.admin.form.annotation.UserEntity;
import com.clone.airbnb.admin.schema.vo.Column;
import com.clone.airbnb.admin.schema.vo.Columns;
import com.clone.airbnb.admin.schema.vo.Entities;
import com.clone.airbnb.admin.schema.vo.Entity;


public class EntityProvider {

	private final List<Class<Object>> classes;
	
	private Entities entities;
	
	
	public EntityProvider(List<Class<Object>> classes) {
		this.classes = classes;
	}
	
	
	
	public Entities getEntities() {
		if (this.entities == null) {
			synchronized (this) {
				if (this.entities == null) {
					this.entities = this.create();
				}
			}
		}
		
		return this.entities;
	}
	
	
	
	/**
	 * Entities 를 생성한다.
	 * 
	 * @return Entities 객체
	 */
	private Entities create() {
		if (this.classes == null || this.classes.size() < 1) {
			throw new EntityFailToLoadClassException("Entity 클래스가 로드되지 않았습니다.");
		};
		
		if (ApplicationContextProvider.getApplicationContext() == null) {
			throw new ApplicationContextBeanCreationException("ApplicationContext 객체가 null 입니다. " + ApplicationContextProvider.class.getName() + " 객체를 생성하여 빈으로 등록하십시오. ");
		}
		
		Entities.Builder builder = new Entities.Builder();
		
		for (int i = 0; i < this.classes.size(); i++) {
			Class<Object> entityClass = this.classes.get(i); 
			
			if (!AdminFormEntity.class.isAssignableFrom(entityClass)) {
				throw new EntitySchemaInvalidEntityClassException("Entity 클래스는 반드시 AdminFormEntity 인터페이스를 상속해야 합니다. Entity[" + entityClass.getName() + "]");
			}
			
			Entity entity = this.createEntity(entityClass);
			
			builder.put(entityClass.getSimpleName(), entity);
		}
		
		return builder.build();
	}
	
	
	
	
	
	/**
	 * Entity 를 생성한다.
	 * 
	 * @param entityName 생성할 Entity 클래스 이름
	 * @return Entity 객체
	 */
	private Entity createEntity(Class<Object> entityClass) {
		Columns.Builder columnsBuilder = new Columns.Builder();
		
		try {
			for(Field f : entityClass.getDeclaredFields()) {
				Column column = createColumn(entityClass, f);
				if (column != null) {
					columnsBuilder.put(column.getName(), column);
				}
			}
			
			Class<Object> superClass = entityClass.getSuperclass();
			
			for(Field f : superClass.getDeclaredFields()) {
				Column column = createColumn(entityClass, f);
				if (column != null) {
					columnsBuilder.put(column.getName(), column);
				}
			}
			
		} catch (Exception e) {
			throw new EntitySchemaCreationException("Entity: " + entityClass.getName() + " Cause Exception!", e);
		}
		
		Columns columns = columnsBuilder.build();
		
		List<String> methods = new ArrayList<>();

		for (Method m : entityClass.getDeclaredMethods()) {
			methods.add(m.getName());
		}
		
		boolean isUserEntity = false;
		
		if (entityClass.getAnnotation(UserEntity.class) != null) {
			isUserEntity = true;
		}
		
		return new Entity(entityClass.getSimpleName(), columns, methods, entityClass, isUserEntity);
	}
	
	
	
	private Column createColumn(Class<Object> entityClass, Field f) {
		Column column = null;
		
		column = new Column(f, entityClass);
		
		if (column.getFormType() != null) {
			return column;
		} else {
			return null;
		}
	}
}
