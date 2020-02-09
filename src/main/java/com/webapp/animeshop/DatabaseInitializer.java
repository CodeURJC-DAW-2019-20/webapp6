package com.webapp.animeshop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.webapp.animeshop.model.Product;
import com.webapp.animeshop.model.User;
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
		
		Product product = new Product("Funko Pop! Levi Ackerman","Attack on Titans",14.49,"WOW",5,9,200,"Funko Pop!","TM0ZZH441XA", "/img/product/levimin.png", "/img/product/levi2.jpg");
		Product product2 = new Product("One Piece - Charlotte Katakuri (Figuarts ZERO)","One Piece",69.99,"WOW",9,16,500,"Figuarts ZERO","TM0ZZH441XB", "/img/product/katakuri.png", "/img/product/katakurifull.jpg");
		Product product3 = new Product("Pack Naruto y Sasuke Relation (Figuarts ZERO Ex)","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC", "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg");
		this.productService.addProduct(product);
		this.productService.addProduct(product2);
		this.productService.addProduct(product3);
	}
}