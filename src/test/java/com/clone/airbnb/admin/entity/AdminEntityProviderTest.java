package com.clone.airbnb.admin.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.admin.schema.AdminEntityProvider;
import com.clone.airbnb.admin.schema.vo.Group;
import com.clone.airbnb.admin.schema.vo.Groups;

@SpringBootTest
public class AdminEntityProviderTest {

	@Autowired
	AdminEntityProvider adminEntityProvider;


	
	@Test
	void groups() {
		Groups groups = adminEntityProvider.getAdminDefinitionObject().getGroups();
		assertNotNull(groups);
		assertTrue(groups.keySet().size() > 0);
		
		Set<String> keys = groups.keySet();
		
		for (String key : keys) {
			Group group = groups.get(key);
			assertNotNull(group);
			
			assertTrue(group.size() > 0);
			assertNotNull(group.get(0));
			
		}
		
	}
	
}
