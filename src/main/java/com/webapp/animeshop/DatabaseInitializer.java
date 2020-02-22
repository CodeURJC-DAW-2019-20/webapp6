package com.webapp.animeshop;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.webapp.animeshop.model.Address;
import com.webapp.animeshop.model.Blog;
import com.webapp.animeshop.model.Order;
import com.webapp.animeshop.model.OrderMetrics;
import com.webapp.animeshop.model.Product;
import com.webapp.animeshop.model.ProductAmount;
import com.webapp.animeshop.model.User;
import com.webapp.animeshop.repositories.OrderRepository;
import com.webapp.animeshop.repositories.ProductAmountRepository;
import com.webapp.animeshop.repositories.UserRepository;
import com.webapp.animeshop.repositories.OrderMetricsRepository;
import com.webapp.animeshop.service.ProductService;
import com.webapp.animeshop.service.BlogService;
import com.webapp.animeshop.service.OrderService;


@Controller
public class DatabaseInitializer {

	private UserRepository userRepository;
	private ProductService productService;
	private BlogService blogService;
	private OrderRepository orderRepository;
	private ProductAmountRepository pAmountRepository;
	private OrderService orderService;
	private OrderMetricsRepository pOrderMetricsRpository;
	
	@Autowired
	public DatabaseInitializer(
	       UserRepository userRepository,
	       ProductService productService,
	       BlogService blogService,
	       OrderRepository orderRepository,
	       ProductAmountRepository pAmountRepository,
	       OrderService pOrderService,
	       OrderMetricsRepository pOrderMetricsRepository
	) {
	       this.userRepository = userRepository;
	       this.productService = productService;
	       this.blogService = blogService;
	       this.orderRepository = orderRepository;
	       this.pAmountRepository = pAmountRepository;
	       this.orderService = pOrderService;
	       this.pOrderMetricsRpository = pOrderMetricsRepository;
	}

	@PostConstruct
	private void initDatabase() {
		// Users
		
		Address address = new Address("David","Amor","URJC","654451912","d.amora@urjc.es","Calle de Uruguay, 6","7D","Fuenlabrada","Españita","28944");
		User user = new User("user", "pass", address, "ROLE_USER");
		user.setDelivery(address);
		this.userRepository.save(user);
		User admin = new User("admin", "adminpass", new Address(), "ROLE_USER", "ROLE_ADMIN");
		this.userRepository.save(admin);

		
		this.productService.addProduct(new Product("Funko Pop! Levi Ackerman","Attack on Titans",14.49,"WOW",5,9,200,"Funko Pop!","TM0ZZH441XA",100, "/img/product/levimin.png", "/img/product/levi2.jpg"));
		this.productService.addProduct(new Product("One Piece - Charlotte Katakuri (Figuarts ZERO)","One Piece",69.99,"WOW",9,16,500,"Figuarts ZERO","TM0ZZH441XB",100, "/img/product/katakuri.png", "/img/product/katakurifull.jpg"));
		this.productService.addProduct(new Product("Pack Naruto y Sasuke Relation (Figuarts ZERO Ex)","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC",100, "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº4","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC",100, "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº5","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC",100, "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº6","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC",100, "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº7","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC",100, "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº8","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC",100, "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº9","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC",100, "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº10","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC",100, "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº11","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC",100, "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		this.productService.addProduct(new Product("Producto Nº12","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC",100, "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		
		/*List<ProductAmount> productList = new ArrayList<ProductAmount>();
		
		for(int i=0; i<this.productService.getProducts().size();i++) {
			productList.add(new ProductAmount(this.productService.getProducts().get(i),1));
		}
		this.orderRepository.save(new Order());
		Order order = new Order(productList,user,1000);
		this.orderRepository.save(order);
		this.orderRepository.findById(18).setProductList(productList);		
		user.setOrderList(this.orderRepository.findAll());*/

		Blog blog = new Blog("Administrador", "Nuevo Funko Pop! de Levi Ackerman - Attack on Titans", "No pierdas la oportunidad de hacerte con esta nueva pieza por parte de Funko Pop! de uno de los personajes más icónicos de Attack on Titans.", "../img/blog/main-blog/levi-banner-icon.png", "¡Buenos días gente! Hoy empezamos la mañana con buenas noticias, y es que gracias a Funko Pop! ya que hoy acaba de lanzar una nueva figura", 1);
		Blog blog2 = new Blog("Administrador", "Nueva figura Naruto Kyuubi mode por parte de Figuarts ZERO", "Y es que otra vez lo han vuelto a hacer.. Figuarts ZERO nos sorprende con esta magnifica figura de Naruto en modo Kyuubi para los más amantes de la serie.", "../img/blog/main-blog/naruto-banner-icon.png", "¡Buenos días gente! Hoy empezamos la mañana con buenas noticias, y es que gracias a Funko Pop! ya que hoy acaba de lanzar una nueva figura", 1);
		Blog blog3 = new Blog("Administrador", "INCREÍBLE busto M.U.B Predator by Tsume", "Precio no apto para cardiacos. Eso sí, es una obra de arte de los piés a la cabeza.", "/img/blog/main-blog/predator-banner-icon.png", "¡Buenos días gente! Hoy empezamos la mañana con buenas noticias, y es que gracias a Funko Pop! ya que hoy acaba de lanzar una nueva figura", 1);
		
		
		
		blog.setProduct(this.productService.getProduct(3));
		
		
		this.productService.addProduct(this.productService.getProduct(5));
		this.productService.addProduct(this.productService.getProduct(6));
		this.productService.addProduct(this.productService.getProduct(7));
		blog.setIdProduct();
		
		this.blogService.addBlog(blog);
		this.blogService.addBlog(blog2);
		this.blogService.addBlog(blog3);
		
		this.orderRepository.save(new Order(new ArrayList<>(),user,0));
		this.orderRepository.save(new Order(new ArrayList<>(),null,0));
		//ProductAmount productAmount = new ProductAmount(this.productService.getProduct(3),this.orderRepository.findById(18),2);
		//this.pAmountRepository.save(productAmount);
		//Order order = new Order(this.productService.getProducts(),user,1000);
		//this.orderRepository.findById(18).setProductList(this.productService.getProducts());		
		user.setOrderList(this.orderRepository.findAll());
		
		OrderMetrics orderMetrics = new OrderMetrics();
		this.orderService.addMetrics(orderMetrics);
		this.pOrderMetricsRpository.save(orderMetrics);
	}
}