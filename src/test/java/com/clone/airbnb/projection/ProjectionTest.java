package com.clone.airbnb.projection;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.clone.airbnb.entity.values.SelectValues;
import com.clone.airbnb.repository.RoomRepository;
import com.clone.airbnb.utils.ReflectionInvocator;

@SpringBootTest
public class ProjectionTest {

	@Autowired
	private SelectValues selectValues;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Test
	void selectValues() {
	}
	
}
