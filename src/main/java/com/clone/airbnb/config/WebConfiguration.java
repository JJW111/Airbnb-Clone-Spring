package com.clone.airbnb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.clone.airbnb.formatter.NullFormatter;
import com.clone.airbnb.formatter.SafeUserByUsernameFormatter;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new NullFormatter());
		registry.addFormatter(new SafeUserByUsernameFormatter());
	}
	
}
