package com.clone.airbnb.admin.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.admin.form.info.FormInfo;
import com.clone.airbnb.admin.schema.EntityProvider;
import com.clone.airbnb.admin.schema.vo.Column;
import com.clone.airbnb.admin.schema.vo.Columns;
import com.clone.airbnb.admin.schema.vo.Entity;
import com.clone.airbnb.admin.schema.vo.FieldType;
import com.clone.airbnb.admin.schema.vo.FormType;
import com.clone.airbnb.admin.schema.vo.Name;

@SpringBootTest
public class EntityProviderTest {

	@Autowired
	EntityProvider entityProvider;
	
	
	
	/**
	 * <ul>테스트 내용
	 * 	<li>Entity 얻기
	 * 	<li>Entity 이름 객체(Name.class) 체크
	 * 	<li>Columns 얻기
	 * 	<li>ColumnSchema 체크
	 * 	<li>Column Type 체크
	 * 	<li>Column 이름 객체(Name.class) 체크
	 * 	<li>Column FieldType 체크
	 * 	<li>Column FormInfo 체크
	 */
	@Test
	void entity() {
		Entity entity = entityProvider.getEntities().get("User");
		assertNotNull(entity);
		
		Name entityName = entity.getName();
		assertNotNull(entityName);
		assertNotNull(entityName.getOrigin());
		assertNotNull(entityName.getAlias());
		
		assertEquals("User", entityName.getOrigin());
		assertEquals("User", entityName.getAlias());
		
		Columns columns = entity.getColumns();
		assertNotNull(columns);
		
		for (String key : columns.keySet()) {
			Column column = columns.get(key);

			assertNotNull(column.getName());
			
			FormType formType = column.getFormType();
			
			if (formType != null) {
				FieldType fieldType = formType.getFieldType();
				assertNotNull(fieldType);
				
				FormInfo formInfo = formType.getFormInfo();
				assertNotNull(formInfo);
			}
		};
		
	}
	
}
