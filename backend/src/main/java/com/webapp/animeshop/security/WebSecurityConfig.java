package com.webapp.animeshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthProvider authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		http.authorizeRequests().antMatchers("/register").permitAll();
		http.authorizeRequests().antMatchers("/newUser").permitAll();
		http.authorizeRequests().antMatchers("/products").permitAll();
		http.authorizeRequests().antMatchers("/product/**").permitAll();
		http.authorizeRequests().antMatchers("/shoppingCart").permitAll();
		http.authorizeRequests().antMatchers("/blogspage").permitAll();
		http.authorizeRequests().antMatchers("/blog/**").permitAll();
		http.authorizeRequests().antMatchers("/randomBlog").permitAll();

		
		http.authorizeRequests().antMatchers("/assets/**").permitAll();

		// Private pages
		
		http.authorizeRequests().antMatchers("/new/**").permitAll();
		http.authorizeRequests().antMatchers("/userPage").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/userEdit").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/confirmation").hasAnyRole("USER","ADMIN");
		http.authorizeRequests().antMatchers("/deleteProduct/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/addProduct").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/deleteBlog/**").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/addBlog").hasAnyRole("ADMIN");
		
		// Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("name");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/error");

		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");

	}

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth)
	            throws Exception {
	        // Database authentication provider
	        auth.authenticationProvider(authenticationProvider);
	    }
	    
	}