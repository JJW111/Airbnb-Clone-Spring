package com.clone.airbnb.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.clone.airbnb.admin.schema.EntityProvider;
import com.clone.airbnb.admin.schema.RepositoryMapperProvider;
import com.clone.airbnb.admin.schema.vo.Entity;
import com.clone.airbnb.admin.schema.vo.Name;
import com.clone.airbnb.admin.schema.vo.RepositoryMapper;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.Currency;
import com.clone.airbnb.entity.enu.Gender;
import com.clone.airbnb.entity.enu.Language;
import com.clone.airbnb.utils.ReflectionInvocator;

@SpringBootTest(properties = "spring.config.location=" 
		+ "classpath:/application-test.yaml")
public class RepositoryMapperTest {
	
	@Autowired
	RepositoryMapperProvider repositoryMapperProvider;
	
	@Autowired
	EntityProvider entityProvider;
	

	
	String username = "jjw@gmail.com";
	String password = "ablksdlkf123";
	String firstName = "Jin won";
	String lastName = "Jeong";
	Gender gender = Gender.OTHER;
	Language language = Language.KOREAN;
	Currency currency = Currency.KRW;
	Boolean superhost = false;
	
	
	
	
	/**
	 * 테스트 목록
	 *  
	 * PagingAndSortingRepository
	 * 	- save
	 * 	- deleteById(One Id)
	 *
	 * RepositoryWrapper
	 * 	- findBy
	 */
	@Test
	void findBy() {
		RepositoryMapper repositoryMapper = repositoryMapperProvider.getRepositoryMapper();
		Entity entity = entityProvider.getEntities().get("User");
		assertNotNull(entity);
		
		Name entityName = entity.getName();
		assertNotNull(entityName);
		
		User user = User.builder()
				.setUsername(username)
				.setPassword(password)
				.setFirstName(firstName)
				.setLastName(lastName)
				.setGender(gender)
				.setLanguage(language)
				.setCurrency(currency)
				.setSuperhost(superhost)
				.build();
		
		PagingAndSortingRepository<Object, Integer> pagingAndSortingRepository = repositoryMapper.get(entityName.getOrigin());
		
		User savedUser = (User) pagingAndSortingRepository.save(user);
		assertNotNull(savedUser);
		
		User foundUser = (User) ReflectionInvocator.invoke(pagingAndSortingRepository, "findByUsername", String.class, username);
		assertNotNull(foundUser);
		
		assertEquals(savedUser, foundUser);
		
		pagingAndSortingRepository.deleteById(foundUser.getId());
		
		User deletedUser = (User) ReflectionInvocator.invoke(pagingAndSortingRepository, "findByUsername", String.class, username);
		assertNull(deletedUser);
	}
	
}
