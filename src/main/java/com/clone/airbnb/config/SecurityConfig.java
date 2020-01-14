package com.clone.airbnb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.clone.airbnb.security.AdminAuthenticationFailureHandler;
import com.clone.airbnb.security.AuthAuthenticationFailureHandler;
import com.clone.airbnb.security.UserAuthenticationProvider;

@Configuration
@EnableWebSecurity
@ComponentScan("com.clone.airbnb.security")
public class SecurityConfig {

	
	@Configuration
	@Order(1)
	public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private UserAuthenticationProvider authenticationProvider;
		
		@Autowired
		@Qualifier("adminAuthenticationFailureHandler")
		private AuthenticationFailureHandler adminAuthenticationFailureHandler;
		
		
		
		@Override
		protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authenticationProvider);
		}
		
		
		
		@Override
		public void configure(WebSecurity web) {
			web.ignoring().antMatchers("/admin/css/**");
		}
		
		
		
		@Override
	    protected void configure(final HttpSecurity http) throws Exception {
	        http
	        	
	        	.antMatcher("/admin/**")
	        	.authorizeRequests().anyRequest().hasRole("ADMIN")
	        	
		        .and()
		        .formLogin()
	          	.loginPage("/admin_login")
	          	.loginProcessingUrl("/admin/session") 	// 컨트롤러에 해당 url 매핑이 존재하지 않음. /admin/** 가 접근 불가라 인식하지 못하는 것 같음
	          	.failureHandler(adminAuthenticationFailureHandler)
	          	.defaultSuccessUrl("/admin")
	          	.usernameParameter("username")
	          	.passwordParameter("password")
	          	
	          	.and()
	          	.logout()
	          	.logoutUrl("/admin/session_out")		// 컨트롤러에 해당 url 매핑이 존재하지 않음. /admin/** 가 접근 불가라 인식하지 못하는 것 같음
	          	.logoutSuccessUrl("/admin_login")
	          	.deleteCookies("JSESSIONID")
	          	
	          	.and()
	          	.csrf().disable();
	    }
		
	}
	
	
	
	@Configuration
	@Order(2)
	public static class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {
	
		@Autowired
		private UserAuthenticationProvider authenticationProvider;
		
		@Autowired
		@Qualifier("authAuthenticationFailureHandler")
		private AuthenticationFailureHandler authAuthenticationFailureHandler;
		
		
		
		@Override
		protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authenticationProvider);
		}
		
		
		
		@Override
	    protected void configure(final HttpSecurity http) throws Exception {
	        http
	        	
	        	.antMatcher("/auth/**")
	          	.authorizeRequests().anyRequest().hasRole("USER")
	          	
		        .and()
		        .formLogin()
	          	.loginPage("/login")
	          	.loginProcessingUrl("/auth/session") // 컨트롤러에 해당 url 매핑이 존재하지 않음. /auth/** 가 접근 불가라 인식하지 못하는 것 같음
	          	.failureHandler(authAuthenticationFailureHandler)
	          	.defaultSuccessUrl("/")
	          	.usernameParameter("username")
	          	.passwordParameter("password")
	          	
	          	.and()
	          	.logout()
	          	.logoutUrl("/auth/session_out")		// 컨트롤러에 해당 url 매핑이 존재하지 않음. /auth/** 가 접근 불가라 인식하지 못하는 것 같음
	          	.logoutSuccessUrl("/")
	          	.deleteCookies("JSESSIONID")
	          	
	          	.and()
	          	.csrf().disable();
	    }
		
	}
	
	
	
	@Configuration
	@Order(3)
	public static class App3ConfigurationAdapter extends WebSecurityConfigurerAdapter {
	
		@Override
	    protected void configure(final HttpSecurity http) throws Exception {
	        http
	        	
	        	.antMatcher("**")
	          	.authorizeRequests().anyRequest().permitAll()
	          	
	          	.and()
	          	.csrf().disable();
	    }
		
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public AuthenticationFailureHandler adminAuthenticationFailureHandler() {
		return new AdminAuthenticationFailureHandler();
	}
	
	
	
	@Bean
	public AuthenticationFailureHandler authAuthenticationFailureHandler() {
		return new AuthAuthenticationFailureHandler();
	}
	
}
