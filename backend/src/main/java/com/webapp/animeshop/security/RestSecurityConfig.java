package com.webapp.animeshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthProvider userRepoAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

	    http.antMatcher("/api/**");
	    
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/logIn").authenticated();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/index").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/blogs").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/blogs/{id}").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/logOut").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/register").permitAll();
		//http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/newUser").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,  "/api/users").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET,  "/api/currentuser").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/products/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/products/{id}/").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/order/**").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/order/**").permitAll(); 
		
		
		// URLs that need authentication to access to it
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/user/**").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/**").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/products/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/blogs/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/blogs/**").hasRole("ADMIN");
		
		// Other URLs can be accessed without authentication
		http.authorizeRequests().anyRequest().permitAll();

		// Disable CSRF protection (it is difficult to implement in REST APIs)
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();

		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Database authentication provider
		auth.authenticationProvider(userRepoAuthProvider);
	}
}