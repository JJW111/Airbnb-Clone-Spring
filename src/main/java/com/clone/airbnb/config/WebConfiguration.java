package com.clone.airbnb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.clone.airbnb.formatter.DateFormatter;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
	@Autowired
	private DateFormatter dateFormatter;
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(dateFormatter);
	}
	
}