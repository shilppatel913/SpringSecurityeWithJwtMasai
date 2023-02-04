package com.aster.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.aster.app.jwt.JwtTokenGeneratorFilter;
import com.aster.app.jwt.JwtTokenValidatorFilter;

@Configuration
public class SecurityConfig {

	@Bean
	  public SecurityFilterChain masaiSecurityConfig(HttpSecurity http) throws Exception {

			/* for jwt authentication */
		
		//disable the session by marking it as stateless
		//addFilterAfter and addFilterBefore BasicAuthenticationFilter
		http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers(HttpMethod.POST,"/customers").permitAll()
		.anyRequest().authenticated()
		.and()
		.addFilterAfter(new JwtTokenGeneratorFilter(),BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtTokenValidatorFilter(),BasicAuthenticationFilter.class)
		.formLogin()
		.and()
		.httpBasic();

			return http.build();
	  }

	
	
	
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
}
