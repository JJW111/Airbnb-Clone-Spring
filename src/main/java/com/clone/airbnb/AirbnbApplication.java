package com.clone.airbnb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages={"com.clone.airbnb.entity"}) 
@EnableJpaRepositories(basePackages={"com.clone.airbnb.repository"})
@ComponentScan(basePackages={
		"com.clone.airbnb.controller",
		"com.clone.airbnb.config",
		"com.clone.airbnb.service",
		})
public class AirbnbApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirbnbApplication.class, args);
	}

}
