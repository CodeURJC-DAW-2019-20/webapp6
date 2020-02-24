package com.webapp.animeshop;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.webapp.animeshop.blog.Blog;
import com.webapp.animeshop.blog.BlogService;
import com.webapp.animeshop.order.Order;
import com.webapp.animeshop.order.OrderMetrics;
import com.webapp.animeshop.order.OrderMetricsRepository;
import com.webapp.animeshop.order.OrderRepository;
import com.webapp.animeshop.order.OrderService;
import com.webapp.animeshop.product.Product;
import com.webapp.animeshop.product.ProductAmount;
import com.webapp.animeshop.product.ProductAmountRepository;
import com.webapp.animeshop.product.ProductService;
import com.webapp.animeshop.user.Address;
import com.webapp.animeshop.user.User;
import com.webapp.animeshop.user.UserRepository;
import com.webapp.animeshop.product.ProductRepository;;


@Controller
public class DatabaseInitializer {

	private UserRepository userRepository;
	private ProductService productService;
	private BlogService blogService;
	private OrderRepository orderRepository;
	private ProductAmountRepository pAmountRepository;
	private OrderService orderService;
	private OrderMetricsRepository pOrderMetricsRpository;
	private ProductRepository productRepository;
	
	@Autowired
	public DatabaseInitializer(
	       UserRepository userRepository,
	       ProductService productService,
	       BlogService blogService,
	       OrderRepository orderRepository,
	       ProductAmountRepository pAmountRepository,
	       OrderService pOrderService,
	       OrderMetricsRepository pOrderMetricsRepository,
	       ProductRepository productRepository
	) {
	       this.userRepository = userRepository;
	       this.productService = productService;
	       this.blogService = blogService;
	       this.orderRepository = orderRepository;
	       this.pAmountRepository = pAmountRepository;
	       this.orderService = pOrderService;
	       this.pOrderMetricsRpository = pOrderMetricsRepository;
	       this.productRepository = productRepository;
	}

	@PostConstruct
	private void initDatabase() {
		// Users
		
		Address address = new Address("David","Amor","URJC","654451912","David.a.almendra@hotmail.com","Calle de Uruguay, 6","7D","Fuenlabrada","Españita","28944");
		User user = new User("user", "pass", address, "ROLE_USER");
		user.setDelivery(address);
		this.userRepository.save(user);
		User admin = new User("admin", "adminpass", new Address(), "ROLE_USER", "ROLE_ADMIN");
		this.userRepository.save(admin);

		
		/*this.productService.addProduct(new Product("Funko Pop! Levi Ackerman","Attack on Titans",14.49,"WOW",5,9,200,"Funko Pop!","TM0ZZH441XA001",100, "/img/product/levimin.png", "/img/product/levi2.jpg"));
		this.productService.addProduct(new Product("Attack on Titans - Mikasa Ackerman","Attack on Titans",40.50,"WOW",50,30,1200,"Figuarts ZERO","TM0ZZH441XC002",160, "/img/product/mikasa.png", "/img/product/mikasa2.jpg"));
		this.productService.addProduct(new Product("Eren Jaeguer","Attack on Titans",50.25,"WOW",31,25,100,"Figuarts ZERO","TM0ZZH441XC003",40, "/img/product/ErenJaeguer.png", "/img/product/ErenJaeguer2.jpg"));
		this.productService.addProduct(new Product("Funko Pop! -Titan Armadura","Attack on Titans",20.00,"WOW",40,19,200,"Funko Pop!","TM0ZZH441XC004",58, "/img/product/titanArmadura.png", "/img/product/titanArmadura2.jpg"));
		this.productService.addProduct(new Product("Funko Pop! -Titan Colosal","Attack on Titans",17.99,"WOW",40,19,200,"Funko Pop!","TM0ZZH441XC005",80, "/img/product/titanColosal.png", "/img/product/titanColosal2.jpg"));
		
		this.productService.addProduct(new Product("Charlotte Katakuri(Figuarts ZERO)","One Piece",69.99,"WOW",15,20,400,"Figuarts ZERO","TM0ZZH441XB006",90, "/img/product/katakuri.png", "/img/product/katakurifull.jpg"));
		this.productService.addProduct(new Product("Funko Pop! - Chopper","One Piece",56.75,"WOW",17,30,650,"Funko Pop!","TM0ZZH441XB007",75, "/img/product/chopper.png", "/img/product/chopper2.jpg"));
		this.productService.addProduct(new Product("Jinbei (Figuarts ZERO)","One Piece",80.20,"WOW",25,40,500,"Figuarts ZERO","TM0ZZH441XB008",60, "/img/product/Jinbei.png", "/img/product/Jinbei2.jpg"));
		this.productService.addProduct(new Product("Luffy (Figuarts ZERO)","One Piece",35.50,"WOW",30,25,750,"Figuarts ZERO","TM0ZZH441XB009",87, "/img/product/Luffy.png", "/img/product/Luffy2.jpg"));
		this.productService.addProduct(new Product("Zoro (Figuarts ZERO)","One Piece",70.00,"WOW",30,30,835,"Figuarts ZERO","TM0ZZH441XB010",120, "/img/product/Zoro.png", "/img/product/Zoro2.jpg"));
		
		this.productService.addProduct(new Product("Funko Pop! - Kakashi","Naruto",17.50,"WOW",5,9,200,"Funko Pop!","TM0ZZH441XC011",70, "/img/product/funko-pop-kakashi.png", "/img/product/funko-pop-kakashi2.png"));
		this.productService.addProduct(new Product("Namikaze (Figuarts ZERO Ex)","Naruto",141.25,"WOW",16,45,1200,"Figuarts ZERO","TM0ZZH441XC012",80, "/img/product/namikaze.png", "/img/product/namikaze2.jpg"));
		this.productService.addProduct(new Product("Naruto Uzumaki","Naruto",59.99,"WOW",12,30,800,"Banpresto","TM0ZZH441XC013",65, "/img/product/naruto.png", "/img/product/naruto2.jpg"));
		this.productService.addProduct(new Product("Naruto sentado (Figuarts ZERO)","Naruto",75.5,"WOW",25,20,500,"Figuarts ZERO","TM0ZZH441XC014",90, "/img/product/narutoSentado.png", "/img/product/narutoSentado2.jpg"));
		this.productService.addProduct(new Product("Pack Naruto y Sasuke Relation (Figuarts ZERO Ex)","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC015",100, "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		
		this.productService.addProduct(new Product("M.U.B Busto Predator","Predator",2899.95,"WOW",45,105,30000,"Tsume","TM0ZZH441XC016",10, "/img/product/predator.png", "/img/product/predator2.png"));
		this.productService.addProduct(new Product("Kyuubi - Uzumaki Naruto x Minato - Kizuna Relation","Naruto",242.07,"WOW",24,27,1101,"Figuarts ZERO","TM0ZZH441XC017",35, "/img/product/naruto-kyuubi2.png", "/img/product/naruto-kyuubi.png"));
		*/
		Product prod1 = new Product("Funko Pop! Levi Ackerman","Attack on Titans",14.49,"WOW",5,9,200,"Funko Pop!","TM0ZZH441XA",100);
		
		prod1.setImage("/img/product/levimin.png");
		prod1.setImagefull("/img/product/levimin.png");
		this.productRepository.save(prod1);
		
		Product prod2 = new Product("Funko Pop! Levi Ackerman","Attack on Titans",14.49,"WOW",5,9,200,"Funko Pop!","TM0ZZH441XA",100/*, "/img/product/levimin.png", "/img/product/levi2.jpg"*/);
		prod2.setImage("/img/product/narutosasuke.png");
		prod2.setImagefull("/img/product/narutosasuke.png");
		this.productRepository.save(prod2);
		
		Product prod3 = new Product("Attack on Titans - Mikasa Ackerman","Attack on Titans",40.50,"WOW",50,30,1200,"Figuarts ZERO","TM0ZZH441XC",160/*, "/img/product/mikasa.png", "/img/product/mikasa2.jpg"*/);
		prod3.setImage("/img/product/mikasa.png");
		prod3.setImagefull("/img/product/mikasa.png");
		this.productRepository.save(prod3);
		
		Product prod4 = new Product("Eren Jaeguer","Attack on Titans",50.25,"WOW",31,25,100,"Figuarts ZERO","TM0ZZH441XC",40/*, "/img/product/ErenJaeguer.png", "/img/product/ErenJaeguer2.jpg"*/);
		prod4.setImage("/img/product/ErenJaeguer.png");
		prod4.setImagefull("/img/product/ErenJaeguer.png");
		this.productRepository.save(prod4);
		
		Product prod5 = new Product("Funko Pop! -Titan Armadura","Attack on Titans",20.00,"WOW",40,19,200,"Funko Pop!","TM0ZZH441XC",58/*, "/img/product/titanArmadura.png", "/img/product/titanArmadura2.jpg"*/);
		prod5.setImage("/img/product/levimin.png");
		prod5.setImagefull("/img/product/levimin.png");
		this.productRepository.save(prod5);
		
		Product prod6 = new Product("Funko Pop! -Titan Colosal","Attack on Titans",17.99,"WOW",40,19,200,"Funko Pop!","TM0ZZH441XC",80/*, "/img/product/titanColosal.png", "/img/product/titanColosal2.jpg"*/);
		prod6.setImage("/img/product/levimin.png");
		prod6.setImagefull("/img/product/levimin.png");
		this.productRepository.save(prod6);
		
		
		Product prod7 = new Product("Charlotte Katakuri(Figuarts ZERO)","One Piece",69.99,"WOW",15,20,400,"Figuarts ZERO","TM0ZZH441XB",90/*, "/img/product/katakuri.png", "/img/product/katakurifull.jpg"*/);
		prod7.setImage("/img/product/levimin.png");
		prod7.setImagefull("/img/product/levimin.png");
		this.productRepository.save(prod7);
		
		Product prod8 = new Product("Funko Pop! - Chopper","One Piece",56.75,"WOW",17,30,650,"Funko Pop!","TM0ZZH441XB",75/*, "/img/product/chopper.png", "/img/product/chopper2.jpg"*/);
		prod8.setImage("/img/product/levimin.png");
		prod8.setImagefull("/img/product/levimin.png");
		this.productRepository.save(prod8);
		
		Product prod9 = new Product( "Jinbei (Figuarts ZERO)","One Piece",80.20,"WOW",25,40,500,"Figuarts ZERO","TM0ZZH441XB",60/*, "/img/product/Jinbei.png", "/img/product/Jinbei2.jpg"*/);
		prod9.setImage("/img/product/levimin.png");
		prod9.setImagefull("/img/product/levimin.png");
		this.productRepository.save(prod9);
		
		Product prod10 = new Product( "Luffy (Figuarts ZERO)","One Piece",35.50,"WOW",30,25,750,"Figuarts ZERO","TM0ZZH441XB",87/*, "/img/product/Luffy.png", "/img/product/Luffy2.jpg"*/);
		prod10.setImage("/img/product/levimin.png");
		prod10.setImagefull("/img/product/levimin.png");
		this.productRepository.save(prod10);
		
		Product prod11 = new Product("Zoro (Figuarts ZERO)","One Piece",70.00,"WOW",30,30,835,"Figuarts ZERO","TM0ZZH441XB",120/*, "/img/product/Zoro.png", "/img/product/Zoro2.jpg"*/);
		prod11.setImage("/img/product/levimin.png");
		prod11.setImagefull("/img/product/levimin.png");
		this.productRepository.save(prod11);
		
		Product prod12 = new Product("Funko Pop! - Kakashi","Naruto",80.57,"WOW",40,25,200,"Funko Pop!","TM0ZZH441XC",70/*, "/img/product/kakashi.png", "/img/product/kakashi2.jpg"*/);
		prod12.setImage("/img/product/levimin.png");
		prod12.setImagefull("/img/product/levimin.png");
		this.productRepository.save(prod12);
		
		//this.productService.addProduct(new Product("Namikaze (Figuarts ZERO Ex)","Naruto",141.25,"WOW",38,27,750,"Figuarts ZERO","TM0ZZH441XC",80, "/img/product/namikaze.png", "/img/product/namikaze2.jpg"));
		//this.productService.addProduct(new Product("Naruto (Figuarts ZERO)","Naruto",59.99,"WOW",50,30,800,"Figuarts ZERO","TM0ZZH441XC",65, "/img/product/naruto.png", "/img/product/naruto2.jpg"));
		//this.productService.addProduct(new Product("Naruto sentado (Figuarts ZERO)","Naruto",75.5,"WOW",25,20,500,"Figuarts ZERO","TM0ZZH441XC",90, "/img/product/narutoSentado.png", "/img/product/narutoSentado2.jpg"));
		//this.productService.addProduct(new Product("Pack Naruto y Sasuke Relation (Figuarts ZERO Ex)","Naruto",141.25,"WOW",38,19,1200,"Figuarts ZERO","TM0ZZH441XC",100, "/img/product/narutosasuke.png", "/img/product/narutosasukefull.jpg"));
		
		/*List<ProductAmount> productList = new ArrayList<ProductAmount>();
		
		for(int i=0; i<this.productService.getProducts().size();i++) {
			productList.add(new ProductAmount(this.productService.getProducts().get(i),1));
		}
		this.orderRepository.save(new Order());
		Order order = new Order(productList,user,1000);
		this.orderRepository.save(order);
		this.orderRepository.findById(18).setProductList(productList);		
		user.setOrderList(this.orderRepository.findAll());*/

		Blog blog = new Blog("Administrador", "Nuevo Funko Pop! de Levi Ackerman - Attack on Titans", "No pierdas la oportunidad de hacerte con esta nueva pieza por parte de Funko Pop! de uno de los personajes más icónicos de Attack on Titans.", "/img/blog/levi-banner-icon.png", "¡Buenos días gente! Hoy empezamos la mañana con buenas noticias, y es que gracias a Funko Pop! ya que hoy acaba de lanzar una nueva figura", 1);
		Blog blog2 = new Blog("Administrador", "Nueva figura Naruto Kyuubi mode por parte de Figuarts ZERO", "Y es que otra vez lo han vuelto a hacer.. Figuarts ZERO nos sorprende con esta magnifica figura de Naruto en modo Kyuubi para los más amantes de la serie.", "/img/blog/naruto-blog-main.png", "Como ya avisamos hace unas semanas, Figuarts ZERO estaba esperando al décimoquinto aniversario de Naruto para darnos una sorpresa.. Y sí señores, ¡lo han conseguido! Échale un vistazo a esta impresionante figura de Naruto en modo Kyubi", 1);
		Blog blog3 = new Blog("Administrador", "INCREÍBLE busto M.U.B Predator by Tsume", "Precio no apto para cardiacos. Eso sí, es una obra de arte de los piés a la cabeza.", "/img/blog/predator-blog-main2.png", "Menudo sorpresón nos acaba de dar Tsume Art. Sin esperarlo, de la noche a la mañana, se ha sacado de la manga éste impresionante busto de la saga Predator. ¡No te lo puedes perder!", 1);
		
		
		
		blog.setProduct(this.productService.getProduct(3));
		blog2.setProduct(this.productService.getProduct(14));
		blog3.setProduct(this.productService.getProduct(18));
		
		
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