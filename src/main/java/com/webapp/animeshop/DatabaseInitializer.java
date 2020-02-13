package com.webapp.animeshop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.webapp.animeshop.model.Blog;
import com.webapp.animeshop.model.Product;
import com.webapp.animeshop.model.User;
import com.webapp.animeshop.repositories.UserRepository;
import com.webapp.animeshop.service.ProductService;
import com.webapp.animeshop.service.BlogService;


@Controller
public class DatabaseInitializer {

	private UserRepository userRepository;
	private ProductService productService;
	private BlogService blogService;
	
	@Autowired
	public DatabaseInitializer(
	       UserRepository userRepository,
	       ProductService productService,
	       BlogService blogService
	) {
	       this.userRepository = userRepository;
	       this.productService = productService;
	       this.blogService = blogService;
	}

	@PostConstruct
	private void initDatabase() {
		// Users
		
		User user = new User("user", "pass", "ROLE_USER");
		User admin = new User("admin", "adminpass", "ROLE_USER", "ROLE_ADMIN");
		this.userRepository.save(user);
		this.userRepository.save(admin);
		
		this.productService.addProduct(new Product("Funko Pop! Levi Ackerman","Attack on Titans",14.49,"WOW",5,9,200,"Funko Pop!","TM0ZZH441XA", "/img/product/levimin.png", "/img/product/levi2.jpg"));
		this.productService.addProduct(new Product("One Piece - Charlotte Katakuri (Figuarts ZERO)","One Piece",69.99,"WOW",9,16,500,"Figuarts ZERO","TM0ZZH441XB", "/img/product/katakuri.png", "/img/product/katakurifull.jpg"));
		this.productService.addProduct(new Product("Pack Naruto y Sasuke Relation (Figuarts ZERO Ex)","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC", "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº4","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC", "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº5","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC", "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº6","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC", "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº7","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC", "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº8","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC", "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº9","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC", "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº10","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC", "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº11","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC", "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº12","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC", "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		
		
		this.blogService.addBlog(new Blog("Administrador", "Nuevo Funko Pop! de Levi Ackerman - Attack on Titans", "No pierdas la oportunidad de hacerte con esta nueva pieza por parte de Funko Pop! de uno de los personajes más icónicos de Attack on Titans.", "img/blog/levi-blog.png"));
		this.blogService.addBlog(new Blog("Administrador", "Nueva figura Naruto Kyuubi mode por parte de Figuarts ZERO", "Y es que otra vez lo han vuelto a hacer.. Figuarts ZERO nos sorprende con esta magnifica figura de Naruto en modo Kyuubi para los más amantes de la serie.", "img/blog/naruto-blog.png"));
		this.blogService.addBlog(new Blog("Administrador", "INCREÍBLE busto M.U.B Predator by Tsume", "Precio no apto para cardiacos. Eso sí, es una obra de arte de los piés a la cabeza.", "img/blog/predator-min.png"));
		
	}
}