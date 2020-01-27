package com.clone.airbnb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.clone.airbnb.annotation.EntityMapping;
import com.clone.airbnb.dto.Authentication;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.entity.enu.LoginMethod;

@EntityMapping(entity=User.class)
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	
	boolean existsByUsername(String username);
	
	
	Optional<User> findByUsername(String username);
	
	
	Optional<User> findByEmailSecret(String emailSecret);
	
	
	<T> List<T> findAllByOrderByIdDesc(Class<T> clazz);
	
	
	<T> Optional<T> findById(Integer id, Class<T> clazz);
	
	
	<T> Optional<T> findByUsername(String username, Class<T> clazz);
	
	
	<T> Optional<T> findByEmailSecret(String emailSecret, Class<T> clazz);
	
	
	<T> Iterable<T> findAllBy(Class<T> clazz);
	
	
	<T> Page<T> findAllBy(Pageable pageable, Class<T> clazz);

	
	@Query( value = "select new com.clone.airbnb.dto.Authentication(u.username, u.password, u.role) from User u where u.username = :username" )
	Optional<Authentication> authenticate(@Param("username") String username);
	
	
	@Query( value = "select u.password from User u where u.username = :username" )
	Optional<String> password(@Param("username") String username);
	
	@Query( value = "select u.firstName from User u where u.username = :username" )
	Optional<String> firstName(@Param("username") String username);
	
	
	@Query( value = "select u.loginMethod from User u where u.username = :username" )
	Optional<LoginMethod> loginMethodOfLoggedUser(@Param("username") String username);
	
}

