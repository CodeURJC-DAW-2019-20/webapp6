package com.webapp.animeshop.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.animeshop.product.ProductAmount;
import com.webapp.animeshop.product.ProductAmountRepository;
import com.webapp.animeshop.product.ProductRepository;
import com.webapp.animeshop.product.ProductService;
import com.webapp.animeshop.user.Address;
import com.webapp.animeshop.user.User;
import com.webapp.animeshop.user.UserComponent;
import com.webapp.animeshop.user.UserRepository;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {
	
	@Autowired
	private ProductAmountRepository pAmountRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
    private OrderService orderService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	public UserComponent userComponent;
	
	@Autowired
	private OrderMetricsRepository orderMetricsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping()
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Order getOrder(@PathVariable long id) {
		return orderRepository.findById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable long id) {
		pAmountRepository.deleteById(id);
	}
	
	@RequestMapping(value = "/{id}/{pid}", method = RequestMethod.DELETE)
	public ResponseEntity<Order> deleteProduct_Aux(@PathVariable long id, @PathVariable long pid) {
		User user = userComponent.getLoggedUser();
		if(user!=null)
			user = userRepository.findByName(user.getName());
		if(this.orderRepository.findById(id)==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if((this.orderRepository.findById(id).getUser()==null && user!=null) || (this.orderRepository.findById(id)!=null && user==null && this.orderRepository.findById(id).getUser()!=null))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		//this.orderService.buildOrders();
		Order order;
		if(user==null)
			order = this.orderRepository.findNotRelated();
		else
			order = this.orderRepository.findById(id);
		if(user!=null && order.getUser().getId()!=user.getId())
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		if(order.getStatus()!=null)
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		ProductAmount pAmount = this.pAmountRepository.findById(pid);
    	if(pAmountRepository.findById(pid)==null)
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	if(pAmountRepository.findById(pAmount.getId()).getOrder().getId()!=order.getId())
    		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    	pAmountRepository.deleteById(pid);
    	order.setTotal();
    	this.orderRepository.save(order);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.POST)
	public ResponseEntity<Order> addProduct(@PathVariable long id, @RequestBody ProductAmount pAmount/*, @PathVariable int qt*/) {
		User user = userComponent.getLoggedUser();
		if(user!=null)
			user = userRepository.findByName(user.getName());
		if(this.orderRepository.findById(id)==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if((this.orderRepository.findById(id).getUser()==null && user!=null) || (this.orderRepository.findById(id)!=null && user==null && this.orderRepository.findById(id).getUser()!=null))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		this.orderService.buildOrders();
		Order order;
		if(user==null)
			order = this.orderRepository.findNotRelated();
		else
			order = this.orderRepository.findById(id);
		if(user!=null && order.getUser().getId()!=user.getId())
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		if(order.getStatus()!=null)
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    	if(productRepository.findById(pAmount.getProduct().getId())==null)
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	this.orderService.addProductToOrder(order.getId(), pAmount.getProduct().getId(), pAmount.getAmount());
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Order> updateProduct(@PathVariable long id, @RequestBody ProductAmount pAmount/*, @PathVariable int qt*/) {
		User user = userComponent.getLoggedUser();
		if(user!=null)
			user = userRepository.findByName(user.getName());
		if(this.orderRepository.findById(id)==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if((this.orderRepository.findById(id).getUser()==null && user!=null) || (this.orderRepository.findById(id)!=null && user==null && this.orderRepository.findById(id).getUser()!=null))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		Order order;
		if(user==null)
			order = this.orderRepository.findNotRelated();
		else
			order = this.orderRepository.findById(id);
		if(user!=null && order.getUser().getId()!=user.getId())
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		if(order.getStatus()!=null)
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		if(pAmountRepository.findById(pAmount.getId()).getOrder().getId()!=order.getId())
    		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    	if(pAmountRepository.findById(pAmount.getId())==null)
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	this.orderService.updateProduct(order.getId(), pAmount.getId(), pAmount.getAmount());
		//pAmount.setTotal(pAmount.getAmount(), pAmount.getProduct().getPrice());
		//pAmountRepository.saveAndFlush(pAmount);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	//public ResponseEntity<User> confirmation(@RequestBody List<Address> json) throws Exception{
	public ResponseEntity<User> confirmation(@PathVariable String shippingname, @PathVariable String lastname,
			@PathVariable String company, @PathVariable String number, @PathVariable String email, 
			@PathVariable String street, @PathVariable String floor, @PathVariable String city,
			@PathVariable String country, @PathVariable String zipcode, @PathVariable String shippingname2, @PathVariable String lastname2,
			@PathVariable String company2, @PathVariable String number2, @PathVariable String email2, 
			@PathVariable String street2, @PathVariable String floor2, @PathVariable String city2,
			@PathVariable String country2, @PathVariable String zipcode2) throws Exception{
    	User user = userComponent.getLoggedUser();
    	Address billing_address = new Address(shippingname, lastname, company, number, email, street, floor, city, country, zipcode);
    	Address delivery = new Address(shippingname2, lastname2, company2, number2, email2, street2, floor2, city2, country2, zipcode2);
    	if(billing_address == null || delivery == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	Order order = this.orderService.confirmOrder(delivery, billing_address, delivery.getName());
    	//this.orderService.sendEmail(user, order, billing_address);
    	OrderMetrics lastMetrics = this.orderMetricsRepository.findAll().get(this.orderMetricsRepository.findAll().size()-1);
    	OrderMetrics orderMetrics = new OrderMetrics(lastMetrics);
    	orderMetrics.newOrder(order);
    	this.orderMetricsRepository.save(orderMetrics);
    	this.orderService.buildOrders();
    	return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/confirmation", method = RequestMethod.POST)
	public ResponseEntity<User> confirmation() throws Exception{
    	User user = userComponent.getLoggedUser();
    	if(user.getDelivery() == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    	Order order = this.orderService.confirmOrder(user.getDelivery(), user.getDelivery(), user.getName());
    	this.orderService.sendEmail(user, order, user.getDelivery());
    	OrderMetrics lastMetrics = this.orderMetricsRepository.findAll().get(this.orderMetricsRepository.findAll().size()-1);
    	OrderMetrics orderMetrics = new OrderMetrics(lastMetrics);
    	orderMetrics.newOrder(order);
    	this.orderMetricsRepository.save(orderMetrics);
    	this.orderService.buildOrders();
    	return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
