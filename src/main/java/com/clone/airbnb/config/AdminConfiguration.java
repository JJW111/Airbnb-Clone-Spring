package com.clone.airbnb.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.clone.airbnb.admin.AdminWebPage;
import com.clone.airbnb.admin.annotation.AdminEntityDefinition;
import com.clone.airbnb.admin.context.ApplicationContextProvider;
import com.clone.airbnb.admin.form.annotation.EntityForm;
import com.clone.airbnb.admin.schema.AdminEntityConfiguration;
import com.clone.airbnb.admin.schema.AdminEntityProvider;
import com.clone.airbnb.admin.schema.EntityProvider;
import com.clone.airbnb.admin.schema.RepositoryMapperProvider;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.common.ClassLoader;



@Configuration
@ComponentScan("com.clone.airbnb.admin.def")
public class AdminConfiguration {

	@Bean
	public ApplicationContextProvider applicationContextProvider() {
		return new ApplicationContextProvider();
	}
	
	@Bean
	public ClassLoader<Object> entityClassLoader() {
		String basePackage = "com.clone.airbnb.entity";
		return new ClassLoader<Object>(basePackage, EntityForm.class);
	}
	
	@Bean
	public ClassLoader<AdminEntityConfiguration> adminConfigClassLoader() {
		String basePackage = "com.clone.airbnb.admin.def";
		return new ClassLoader<AdminEntityConfiguration>(basePackage, AdminEntityDefinition.class);
	}

	@Bean
	public AdminEntityProvider adminEntityProvider(ClassLoader<AdminEntityConfiguration> adminConfigClassLoader) {
		return new AdminEntityProvider(adminConfigClassLoader.getClasses());
	}
	
	@Bean
	public EntityProvider entityProvider(ClassLoader<Object> entityClassLoader) {
		return  new EntityProvider(entityClassLoader.getClasses());
	}
	
	
	@Bean
	public ClassLoader<PagingAndSortingRepository<Object, Integer>> entityRepositoryClassLoader() {
		String basePackage = "com.clone.airbnb.repository";
		return new ClassLoader<PagingAndSortingRepository<Object, Integer>>(basePackage, EntityMapping.class);
	}
	
	
	@Bean
	public RepositoryMapperProvider repositoryMapperProvider(ClassLoader<PagingAndSortingRepository<Object, Integer>> entityRepositoryClassLoader) {
		return new RepositoryMapperProvider(entityRepositoryClassLoader.getClasses());
	}
	
	
	@Bean
	public AdminWebPage adminWebPage(AdminEntityProvider adminEntityProvider, EntityProvider entityProvider, RepositoryMapperProvider repositoryMapperProvider) {
		return new AdminWebPage(adminEntityProvider, entityProvider, repositoryMapperProvider);
	}
	
}
