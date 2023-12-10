package com.ascend.capstone.pdp.security;

import com.ascend.capstone.pdp.security.jwt.AuthEntryPointJwt;
import com.ascend.capstone.pdp.security.jwt.AuthTokenFilter;
import com.ascend.capstone.pdp.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity

public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
  @Autowired
  UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }


  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       
      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
  }

  
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  
  @SuppressWarnings({ "deprecation", "removal" })
@Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {



	  	http
	  		.csrf().disable()
	  		.authorizeRequests()
	  		.requestMatchers("/api/products/**").permitAll()
	  		.requestMatchers("/api/auth/signin").permitAll()
	  		.requestMatchers("/api/auth/add").permitAll()
	  		.anyRequest().authenticated()
	  		.and()
	  		.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
	  		.sessionManagement()
	  		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	  		;
	    http.csrf((csrf) -> csrf
	            .ignoringRequestMatchers("/api/auth/**"))
        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
    
    return http.build();
  }  
}
