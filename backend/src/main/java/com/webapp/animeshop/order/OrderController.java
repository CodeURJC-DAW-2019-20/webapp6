package com.webapp.animeshop.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.webapp.animeshop.user.Address;
import com.webapp.animeshop.user.User;
import com.webapp.animeshop.user.UserComponent;
import com.webapp.animeshop.web.WebController;

@Controller
public class OrderController extends WebController{
	
	@Autowired
    private OrderService orderService;
	
	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
	private UserComponent userSession;
    
    @Autowired
    private OrderMetricsRepository orderMetricsRepository;
    
    @RequestMapping("/shoppingCart")
	public String shoppingCart(Model model) {
    	Order order;
		User user = userSession.getLoggedUser();
		boolean empty = false;
		if(user!=null) {
			order = this.orderRepository.findByStatus(user.getId());
			order.setTotal();
			this.orderRepository.save(order);
			model.addAttribute("order",order);
		}
		else {
			order = this.orderRepository.findNotRelated();
			order.setTotal();
			this.orderRepository.save(order);
			model.addAttribute("order",order);
		}
		if(order.getProductList().isEmpty())
			empty = true;
		model.addAttribute("empty", !empty);
		model.addAttribute("user", user);
		model.addAttribute("cartSize",orderService.getCartSize());
		return "cart";
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
    	User user = userSession.getLoggedUser();
    	Order order;
    	if(user!=null)
    		order = this.orderRepository.findByStatus(user.getId());
    	else
    		order = this.orderRepository.findNotRelated();
        this.orderService.deleteProductFromOrder(order.getId(), id);
        return this.shoppingCart(model);
    }
    
    @PostMapping(value="/updateProduct/{id}")
    public String updateProduct(Model model, @PathVariable("id") long id, @RequestParam("qty") int qt) {
    	User user = userSession.getLoggedUser();
    	Order order;
    	if(user!=null)
    		order = this.orderRepository.findByStatus(user.getId());
    	else
    		order = this.orderRepository.findNotRelated();
		this.orderService.updateProduct(order.getId(), id, qt);
		return this.shoppingCart(model);
    }
    
    @RequestMapping("/checkout")
    public String checkout(Model model) {
    	User user = userSession.getLoggedUser();
    	Order order = this.orderRepository.findNotRelated();
    	if(user!=null)
    		order = this.orderRepository.findByStatus(user.getId());
    	order.setTotal();
		this.orderRepository.save(order);
    	model.addAttribute("order", order);
    	model.addAttribute("user",user);
    	model.addAttribute("cartSize",orderService.getCartSize());
		return "checkout";
    }
    
    @RequestMapping("/confirmation")
    public String confirmation(Model model,@RequestParam String shippingname, Address address, @RequestParam String shippingname2, @RequestParam String lastname2,
			 @RequestParam String company2, @RequestParam String number2, @RequestParam String email2, 
			 @RequestParam String street2, @RequestParam String floor2, @RequestParam String city2,
			 @RequestParam String country2, @RequestParam String zipcode2) throws Throwable {
    	Address billing_address = new Address(shippingname2,lastname2,company2,number2,email2,street2,floor2,city2,country2,zipcode2);
    	User user = userSession.getLoggedUser();
    	Order order = this.orderService.confirmOrder(address, billing_address, shippingname);
    	this.orderService.sendEmail(user, order, billing_address);
    	OrderMetrics lastMetrics = this.orderMetricsRepository.findAll().get(this.orderMetricsRepository.findAll().size()-1);
    	OrderMetrics orderMetrics = new OrderMetrics(lastMetrics);
    	orderMetrics.newOrder(order);
    	this.orderMetricsRepository.save(orderMetrics);
    	this.orderService.buildOrders();
    	model.addAttribute("order", order);
    	model.addAttribute("user",user);
    	model.addAttribute("billing", billing_address);
    	model.addAttribute("cartSize",orderService.getCartSize());
		return "confirmation";
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