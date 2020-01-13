package com.clone.airbnb.utils;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.clone.airbnb.entity.User;
import com.clone.airbnb.repository.UserRepository;

@SpringBootTest
public class BeanUtilsTest {
	
	@Test
	void getBean() {
		@SuppressWarnings("unchecked")
		PagingAndSortingRepository<User, Integer> repo = (PagingAndSortingRepository<User, Integer>) BeanUtils.getBean(UserRepository.class);
		assertNotNull(repo);
	}
	
}
