package com.webapp.animeshop.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.animeshop.order.OrderMetrics;
import com.webapp.animeshop.order.OrderMetricsRepository;
import com.webapp.animeshop.order.OrderService;
import com.webapp.animeshop.web.WebController;


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
	private OrderService orderService;
	
	@Autowired
	private OrderMetricsRepository orderMetricsRepository;

	@PostMapping("/register")
	public String addNewUser(Model model, HttpServletRequest req, @RequestParam String name,
			@RequestParam String email, @RequestParam String password) throws Exception {
		User newUser = new User(name, password,new Address(), "ROLE_USER");
		boolean exists = false;
		if(this.userRepository.findByName(newUser.getName())!=null){
			exists = true;
			model.addAttribute("exists", exists);
			return "/register";
		}
		newUser.getDelivery().setEmail(email);
		userService.save(newUser);
		userService.sendEmail(newUser);
		model.addAttribute("cartSize",orderService.getCartSize());
		return "/login";
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
		model.addAttribute("cartSize",orderService.getCartSize());
		return "/userPage";
	}
	
	
	@GetMapping("/currentuser")
	public User getCurrentUser() {
		return userSession.getLoggedUser();
	}
	
	@RequestMapping("/userEdit")
	public String userEditPage(Model model) {
		if(userComponent.isLoggedUser()) {
			model.addAttribute("user", this.userComponent.getLoggedUser());
		}
		model.addAttribute("cartSize",orderService.getCartSize());
		return "/userEdit";
	}
	
	@RequestMapping("/newInfo")
	public String userNewInfo(Model model,@RequestParam String shippingname, Address address, @RequestParam String password) {
		/*if(userComponent.isLoggedUser()) {
			model.addAttribute("user", this.userComponent.getLoggedUser());
		}*/
		User user = this.userComponent.getLoggedUser();
		user.setDelivery(address);
		user.getDelivery().setName(shippingname);
		user.setPassword(password);
		//User newUser = new User(user.getName(), password,address, "ROLE_USER");
		//this.userRepository.deleteById(user.getId());
		this.userRepository.save(user);
		model.addAttribute("user", user);
		model.addAttribute("cartSize",orderService.getCartSize());
		return "/userPage";
	}
	
	
}