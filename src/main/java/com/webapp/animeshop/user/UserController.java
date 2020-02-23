package com.webapp.animeshop.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.animeshop.order.Order;
import com.webapp.animeshop.order.OrderMetrics;
import com.webapp.animeshop.order.OrderMetricsRepository;
import com.webapp.animeshop.order.OrderRepository;
import com.webapp.animeshop.web.WebController;

import antlr.collections.List;


@Controller
public class UserController extends WebController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserComponent userSession;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderMetricsRepository orderMetricsRepository;

	@PostMapping("/register")
	public String addNewUser(Model model, HttpServletRequest req, @RequestParam String name,
			@RequestParam String password) {

		User newUser = new User(name, password,new Address(), "ROLE_USER");
		/*Order order = new Order();
		Order newOrder = new Order(new ArrayList<>(),null,0);
		if (userRepository.findByName(name) == null) {*/
			userService.save(newUser);
			/*order = this.orderRepository.findNotRelated();
			order.setUser(newUser);
			this.orderRepository.save(order);
			this.orderRepository.save(newOrder);
			
		}*/return "/login"; //else
			//return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	/*public void addUserToModel(Model model) {
		boolean logged = userSession.getLoggedUser() != null;
		model.addAttribute("logged", logged);
	}*/
	
	@RequestMapping("/user")
	public String userPage(Model model) {
		
		System.out.println("wwww");
		if(userComponent.isLoggedUser()) {
			model.addAttribute("user", this.userComponent.getLoggedUser());
		}
		ArrayList<OrderMetrics> list = (ArrayList<OrderMetrics>) this.orderMetricsRepository.findAll();
		list.remove(0);
		model.addAttribute("metric", list);
		OrderMetrics lastMetrics = this.orderMetricsRepository.findAll().get(this.orderMetricsRepository.findAll().size()-1);
		model.addAttribute("average", (int) lastMetrics.getAverage());
		return "userPage";
	}
	
	
	@GetMapping("/currentuser")
	public User getCurrentUser() {
		return userSession.getLoggedUser();
	}
}