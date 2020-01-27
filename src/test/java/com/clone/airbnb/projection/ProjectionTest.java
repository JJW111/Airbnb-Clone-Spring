package com.clone.airbnb.projection;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.entity.projection.impl.SelectUserClass;
import com.clone.airbnb.entity.values.SelectValues;
import com.clone.airbnb.utils.ReflectionInvocator;

@SpringBootTest
public class ProjectionTest {

	@Autowired
	private SelectValues selectValues;
	
	@Test
	void selectValues() {
		List<SelectUserClass> list = selectValues.users();
		
		list.get(0).setUsername("abcd@airbnb-clone.com");
	}
	
}
