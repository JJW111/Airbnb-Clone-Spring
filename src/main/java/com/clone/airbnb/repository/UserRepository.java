package com.clone.airbnb.repository;

import java.util.List;
import java.util.Optional;

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
	
	
	List<User> findAllByOrderByIdDesc();
	
	
	@Query( value = "select new com.clone.airbnb.dto.Authentication(u.username, u.password, u.role) from User u where u.username = :username" )
	Optional<Authentication> authenticate(@Param("username") String username);
	
	
	@Query( value = "select u.password from User u where u.username = :username" )
	Optional<String> password(@Param("username") String username);
	
	@Query( value = "select u.firstName from User u where u.username = :username" )
	Optional<String> firstName(@Param("username") String username);
	
	
	@Query( value = "select u.loginMethod from User u where u.username = :username" )
	Optional<LoginMethod> loginMethodOfLoggedUser(@Param("username") String username);
	
}

