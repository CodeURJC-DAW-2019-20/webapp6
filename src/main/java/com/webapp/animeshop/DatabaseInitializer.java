package com.webapp.animeshop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.webapp.animeshop.model.Product;
import com.webapp.animeshop.model.User;
import com.webapp.animeshop.repositories.ProductRepository;
import com.webapp.animeshop.repositories.UserRepository;
import com.webapp.animeshop.service.ProductService;


@Controller
public class DatabaseInitializer {

	private UserRepository userRepository;
	private ProductService productService;
	
	@Autowired
	public DatabaseInitializer(
	       UserRepository userRepository,
	       ProductService productService
	) {
	       this.userRepository = userRepository;
	       this.productService = productService;

	}

	@PostConstruct
	private void initDatabase() {
		// Users
		
		User user = new User("user", "pass", "ROLE_USER");
		User admin = new User("admin", "adminpass", "ROLE_USER", "ROLE_ADMIN");
		this.userRepository.save(user);
		this.userRepository.save(admin);
		
		Product product = new Product("Funko Pop! Levi Ackerman","Attack on Titans",14.50,"WOW",5,9,200,"Funko Pop!","TM0ZZH441XA");
		this.productService.addProduct(product);
	}
}