package com.clone.airbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.clone.airbnb.entity.values.SelectValues;

@Configuration
@ComponentScan("com.clone.airbnb.formatter")
public class CommonConfiguration {
	
	@Bean
	public SelectValues selectValues() {
		return new SelectValues();
	}
	
}
