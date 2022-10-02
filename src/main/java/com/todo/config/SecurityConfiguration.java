package com.todo.config;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.todo.auth.ApplicationRequestFilter;
import com.todo.auth.ApplicationUserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private ApplicationUserDetailsService applicationUserDetailsService;
	
	@Autowired
	private ApplicationRequestFilter applicationRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(applicationUserDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable()
		.authorizeRequests()
        .antMatchers(
			"/**/authenticate",
			"/**/utilisateurs/changermdp/",
			"/**/utilisateurs/email/**",
            "/v2/api-docs", 
    		"/swagger-resources",
            "/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
            "/swagger-ui.html",
    		"/webjars/**",
			"/v3/api-docs/**",
			"/swagger-ui/**")
         .permitAll()
	     .anyRequest()
	     .authenticated()
	     .and().cors().configurationSource(request-> {
	            CorsConfiguration configuration = new CorsConfiguration();
	            configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200/"));
	            configuration.setAllowedMethods(Arrays.asList("*"));
	            configuration.setAllowedHeaders(List.of("*"));
	            return configuration;
	        })
	     .and()
	     .sessionManagement()
		 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 
		http.addFilterBefore(applicationRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	
	
	@Bean
	public AuthenticationManager AuthenticationManager() throws Exception {
		return authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}