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
	
	@GetMapping()
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Order getOrder(@PathVariable long id) {
		return orderRepository.findById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable long id) {
		pAmountRepository.deleteById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<Order> addProduct(@PathVariable long id, @RequestBody ProductAmount pAmount/*, @PathVariable int qt*/) {
		User user = userComponent.getLoggedUser();
		if(this.orderRepository.findById(id)==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	Order order = this.orderRepository.findById(id);
    	order.setUser(user);
    	this.orderRepository.saveAndFlush(order);
    	if(productRepository.findById(pAmount.getProduct().getId())==null)
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	this.orderService.addProductToOrder(order.getId(), pAmount.getProduct().getId(), pAmount.getAmount());
		//pAmount.setTotal(pAmount.getAmount(), pAmount.getProduct().getPrice());
		//pAmountRepository.saveAndFlush(pAmount);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Order> updateProduct(@PathVariable long id, @RequestBody ProductAmount pAmount/*, @PathVariable int qt*/) {
		if(this.orderRepository.findById(id)==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	Order order = this.orderRepository.findById(id);
    	if(pAmountRepository.findById(pAmount.getId())==null)
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	this.orderService.updateProduct(order.getId(), pAmount.getId(), pAmount.getAmount());
		//pAmount.setTotal(pAmount.getAmount(), pAmount.getProduct().getPrice());
		//pAmountRepository.saveAndFlush(pAmount);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/confirmation", method = RequestMethod.POST)
	public ResponseEntity<User> confirmation() throws Exception{
    	Address billing_address = new Address();
    	User user = userComponent.getLoggedUser();
    	Order order = this.orderService.confirmOrder(billing_address, billing_address, "pepe");
    	//this.orderService.sendEmail(user, order, billing_address);
    	OrderMetrics lastMetrics = this.orderMetricsRepository.findAll().get(this.orderMetricsRepository.findAll().size()-1);
    	OrderMetrics orderMetrics = new OrderMetrics(lastMetrics);
    	orderMetrics.newOrder(order);
    	this.orderMetricsRepository.save(orderMetrics);
    	return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
