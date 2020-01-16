package com.clone.airbnb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.dto.Authentication;
import com.clone.airbnb.entity.User;

@EntityMapping(entity=User.class)
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	
	boolean existsByUsername(String username);
	
	
	User findByUsername(String username);
	
	
	User findByEmailSecret(String emailSecret);
	
	
	<T> T findById(Integer id, Class<T> clazz);
	
	
	<T> T findByUsername(String username, Class<T> clazz);
	
	
	<T> T findByEmailSecret(String emailSecret, Class<T> clazz);
	
	
	<T> Iterable<T> findAllBy(Class<T> clazz);
	
	
	<T> Page<T> findAllBy(Pageable pageable, Class<T> clazz);

	
	<T> Page<T> findByOrderByIdAsc(Pageable pageable, Class<T> clazz);
	
	
	<T> Page<T> findByOrderByIdDesc(Pageable pageable, Class<T> clazz);
	
	
	@Query( value = "select new com.clone.airbnb.dto.Authentication(u.username, u.password, u.role) from User u where u.username = :username" )
	Authentication authenticate(@Param("username") String username);
	
	@Query( value = "select u.password from User u where u.username = :username" )
	String password(@Param("username") String username);
	
}

