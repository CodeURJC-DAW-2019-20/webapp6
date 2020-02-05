package com.webapp.animeshop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.webapp.animeshop.model.User;
import com.webapp.animeshop.model.Product;
import com.webapp.animeshop.repositories.ProductRepository; 
import com.webapp.animeshop.repositories.UserRepository;

@Controller
public class DatabaseInitializer {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	private void initDatabase() {
		// Users
		
		User user = new User("user", "pass", "ROLE_USER");
		User admin = new User("admin", "adminpass", "ROLE_USER", "ROLE_ADMIN");
		userRepository.save(user);
		userRepository.save(admin);
		
	}
}