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
		
		Product product = new Product("Funko Pop! Levi Ackerman","Attack on Titans",14.49,"WOW",5,9,200,"Funko Pop!","TM0ZZH441XA", "/img/product/levimin.png", "/img/product/levi2.jpg");
		Product product2 = new Product("One Piece - Charlotte Katakuri (Figuarts ZERO)","One Piece",69.99,"WOW",9,16,500,"Figuarts ZERO","TM0ZZH441XB", "/img/product/katakuri.png", "/img/product/katakurifull.jpg");
		Product product3 = new Product("Pack Naruto y Sasuke Relation (Figuarts ZERO Ex)","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC", "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg");
		
		
		Blog blog = new Blog("Administrador", "Nuevo Funko Pop! de Levi Ackerman - Attack on Titans", "No pierdas la oportunidad de hacerte con esta nueva pieza por parte de Funko Pop! de uno de los personajes más icónicos de Attack on Titans.", "../img/blog/main-blog/levi-banner-icon.png", "¡Buenos días gente! Hoy empezamos la mañana con buenas noticias, y es que gracias a Funko Pop! ya que hoy acaba de lanzar una nueva figura");
		Blog blog2 = new Blog("Administrador", "Nueva figura Naruto Kyuubi mode por parte de Figuarts ZERO", "Y es que otra vez lo han vuelto a hacer.. Figuarts ZERO nos sorprende con esta magnifica figura de Naruto en modo Kyuubi para los más amantes de la serie.", "../img/blog/main-blog/naruto-banner-icon.png", "¡Buenos días gente! Hoy empezamos la mañana con buenas noticias, y es que gracias a Funko Pop! ya que hoy acaba de lanzar una nueva figura");
		Blog blog3 = new Blog("Administrador", "INCREÍBLE busto M.U.B Predator by Tsume", "Precio no apto para cardiacos. Eso sí, es una obra de arte de los piés a la cabeza.", "/img/blog/main-blog/predator-banner-icon.png", "¡Buenos días gente! Hoy empezamos la mañana con buenas noticias, y es que gracias a Funko Pop! ya que hoy acaba de lanzar una nueva figura");
		
		
		
		blog.setProduct(product);
		
		
		this.productService.addProduct(product);
		this.productService.addProduct(product2);
		this.productService.addProduct(product3);
		blog.setIdProduct();
		
		this.blogService.addBlog(blog);
		this.blogService.addBlog(blog2);
		this.blogService.addBlog(blog3);
		
	}
}