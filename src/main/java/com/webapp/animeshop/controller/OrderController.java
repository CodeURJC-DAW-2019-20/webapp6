package com.webapp.animeshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webapp.animeshop.model.Address;
import com.webapp.animeshop.model.Order;
import com.webapp.animeshop.model.OrderMetrics;
import com.webapp.animeshop.model.Product;
import com.webapp.animeshop.model.ProductAmount;
import com.webapp.animeshop.model.User;
import com.webapp.animeshop.repositories.OrderMetricsRepository;
import com.webapp.animeshop.repositories.OrderRepository;
import com.webapp.animeshop.repositories.ProductRepository;
import com.webapp.animeshop.repositories.UserRepository;
import com.webapp.animeshop.service.OrderService;
import com.webapp.animeshop.user.UserComponent;

@Controller
public class OrderController{
	
	@Autowired
    private OrderService orderService;
	
	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private UserComponent userSession;

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private OrderMetricsRepository orderMetricsRepository;
    
    @RequestMapping("/shoppingCart")
	public String shoppingCart(Model model) {
		//List<Product> products = this.orderRepository.findById(id).getProductList();
    	Order order;
		User user = userSession.getLoggedUser();
		if(user!=null) {
			order = this.orderRepository.findByStatus(user.getId());
			//order = this.orderRepository.findById(user.getOrderList().get(user.getOrderList().size()-1).getId());
			model.addAttribute("order",order);
		}
		else {
			order = this.orderRepository.findNotRelated();
			model.addAttribute("order",order);
		}
		model.addAttribute("user", user);
		return "/cart";
	}
    
    @PostMapping(value="/addFromProduct/{id}")
    public String addProductToCart2(Model model, @PathVariable("id") long id, @RequestParam("qt") int qt) {
    	User user = userSession.getLoggedUser();
    	Order order;
    	if(user!=null)
    		order = this.orderRepository.findByStatus(user.getId());
    	else
    		order = this.orderRepository.findNotRelated();
		this.orderService.addProductToOrder(order.getId(), id, qt);
		return this.shoppingCart(model);
    }

    @PostMapping("/removeCartProduct/{id}")
    public String removeProductFromCart(Model model, @PathVariable("id") long id) {
        long oId = 19;
        this.orderService.deleteProductFromOrder(oId, id);
        return this.shoppingCart(model);
    }
    
    @RequestMapping("/checkout")
    public String checkout(Model model) {
    	User user = userSession.getLoggedUser();
    	Order order = this.orderRepository.findByStatus(user.getId());
    	model.addAttribute("order", order);
    	model.addAttribute("user",user);
		return "/checkout";
    }
    
    @RequestMapping("/confirmation")
    public String confirmation(Model model,Address address, @RequestParam String shippingname2, @RequestParam String lastname2,
			 @RequestParam String company2, @RequestParam String number2, @RequestParam String email2, 
			 @RequestParam String street2, @RequestParam String floor2, @RequestParam String city2,
			 @RequestParam String country2, @RequestParam String zipcode2) {
    	Address billing_address = new Address(shippingname2,lastname2,company2,number2,email2,street2,floor2,city2,country2,zipcode2);
    	User user = userSession.getLoggedUser();
    	Order order = this.orderRepository.findByStatus(user.getId());
    	order.setStatus("Completado");
    	this.orderRepository.save(order);
    	OrderMetrics orderMetrics = this.orderMetricsRepository.findAll().get(0);
    	orderMetrics.newOrder(order);
    	this.orderMetricsRepository.save(orderMetrics);
    	user.getOrderList().add(order);
    	this.userRepository.save(user);
    	Order newOrder = new Order();
    	newOrder.setUser(user);
    	this.orderRepository.save(newOrder);
    	model.addAttribute("order", order);
    	model.addAttribute("user",user);
    	model.addAttribute("billing", billing_address);
		return "/confirmation";
    }
    
    /*

    @PostMapping("/shoppingCart/checkout/{address}")
    public String checkout(@PathVariable("address") String address) {
    	List<Product> p = new ArrayList<>();
    	List<Menu> m = new ArrayList<>();
    	
    	for (ProductAmount pa: shoppingCartService.getProductsInShoppingCart()) {
    		for (int i = 0; i < pa.getAmount(); i++) {
    			p.add(pa.getProduct());
    		}
    	}
    	for (MenuAmount ma: shoppingCartService.getMenuInShoppingCart()) {
    		for (int i = 0; i < ma.getAmount(); i++) {
    			m.add(ma.getProduct());
    		}
    	}
    	
    	shoppingCartService.checkout(new ShoppingCart(address, p, m));
        return "/shoppingCart";
    }*/
	

}