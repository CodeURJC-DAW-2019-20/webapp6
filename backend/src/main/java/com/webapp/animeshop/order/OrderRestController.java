package com.webapp.animeshop.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.animeshop.product.ProductAmount;
import com.webapp.animeshop.product.ProductAmountRepository;
import com.webapp.animeshop.product.ProductRepository;
import com.webapp.animeshop.user.Address;
import com.webapp.animeshop.user.User;
import com.webapp.animeshop.user.UserComponent;
import com.webapp.animeshop.user.UserRepository;

@RestController
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
	public UserComponent userComponent;
	
	@Autowired
	private OrderMetricsRepository orderMetricsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/api/order")
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}
	
	@GetMapping("/api/order/{id}")
	public Order getOrder(@PathVariable long id) {
		if(id==0) {
			Order order;
			User user = userComponent.getLoggedUser();
			if(user!=null) {
				order = this.orderRepository.findByStatus(user.getId());
			}
			else {
				order = this.orderRepository.findNotRelated();
			}
			return order;
		}
		return orderRepository.findById(id);
	}
	
	@RequestMapping(value = "/api/order/{id}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable long id) {
		pAmountRepository.deleteById(id);
	}
	
	@RequestMapping(value = "/api/order/{id}/{pid}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct_Aux(@PathVariable long id, @PathVariable long pid) {
		User user = userComponent.getLoggedUser();
		if(user!=null)
			user = userRepository.findByName(user.getName());
		if(this.orderRepository.findById(id)==null)
			return new ResponseEntity<>("This order does not exist", HttpStatus.NOT_FOUND);
		if((this.orderRepository.findById(id).getUser()==null && user!=null) || (this.orderRepository.findById(id)!=null && user==null && this.orderRepository.findById(id).getUser()!=null))
			return new ResponseEntity<>("This order ID does not correspond to actual user", HttpStatus.FORBIDDEN);
		//this.orderService.buildOrders();
		Order order;
		if(user==null)
			order = this.orderRepository.findNotRelated();
		else
			order = this.orderRepository.findById(id);
		if(user!=null && order.getUser().getId()!=user.getId())
			return new ResponseEntity<>("This user does not contains this order", HttpStatus.FORBIDDEN);
		if(order.getStatus()!=null)
			return new ResponseEntity<>("This order is already completed", HttpStatus.FORBIDDEN);
		ProductAmount pAmount = this.pAmountRepository.findById(pid);
    	if(pAmountRepository.findById(pid)==null)
    		return new ResponseEntity<>("This product does not exist in any order", HttpStatus.NOT_FOUND);
    	if(pAmountRepository.findById(pAmount.getId()).getOrder().getId()!=order.getId())
    		return new ResponseEntity<>("This order does ont contains this product", HttpStatus.FORBIDDEN);
    	pAmountRepository.deleteById(pid);
    	order.setTotal();
    	this.orderRepository.save(order);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/order/{id}",method = RequestMethod.POST)
	public ResponseEntity<?> addProduct(@PathVariable long id, @RequestBody ProductAmount pAmount/*, @PathVariable int qt*/) {
		User user = userComponent.getLoggedUser();
		if(user!=null)
			user = userRepository.findByName(user.getName());
		if(this.orderRepository.findById(id)==null)
			return new ResponseEntity<>("This order does not exist", HttpStatus.NOT_FOUND);
		if((this.orderRepository.findById(id).getUser()==null && user!=null) || (this.orderRepository.findById(id)!=null && user==null && this.orderRepository.findById(id).getUser()!=null))
			return new ResponseEntity<>("This user does not contains this order", HttpStatus.FORBIDDEN);
		this.orderService.buildOrders();
		Order order;
		if(user==null)
			order = this.orderRepository.findNotRelated();
		else
			order = this.orderRepository.findById(id);
		if(user!=null && order.getUser().getId()!=user.getId())
			return new ResponseEntity<>("This user does not contains this order",HttpStatus.FORBIDDEN);
		if(order.getStatus()!=null)
			return new ResponseEntity<>("This order is already completed",HttpStatus.FORBIDDEN);
    	if(productRepository.findById(pAmount.getProduct().getId())==null)
    		return new ResponseEntity<>("This product does not exist",HttpStatus.NOT_FOUND);
    	this.orderService.addProductToOrder(order.getId(), pAmount.getProduct().getId(), pAmount.getAmount());
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/order/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduct(@PathVariable long id, @RequestBody ProductAmount pAmount/*, @PathVariable int qt*/) {
		User user = userComponent.getLoggedUser();
		if(user!=null)
			user = userRepository.findByName(user.getName());
		if(this.orderRepository.findById(id)==null)
			return new ResponseEntity<>("This order does not exist",HttpStatus.NOT_FOUND);
		if((this.orderRepository.findById(id).getUser()==null && user!=null) || (this.orderRepository.findById(id)!=null && user==null && this.orderRepository.findById(id).getUser()!=null))
			return new ResponseEntity<>("This user does not contains this order",HttpStatus.FORBIDDEN);
		Order order;
		if(user==null)
			order = this.orderRepository.findNotRelated();
		else
			order = this.orderRepository.findById(id);
		if(user!=null && order.getUser().getId()!=user.getId())
			return new ResponseEntity<>("This user does not contains this order",HttpStatus.FORBIDDEN);
		if(order.getStatus()!=null)
			return new ResponseEntity<>("This order is already completed",HttpStatus.FORBIDDEN);
		if(pAmountRepository.findById(pAmount.getId()).getOrder().getId()!=order.getId())
    		return new ResponseEntity<>("This order does not contain this product",HttpStatus.FORBIDDEN);
    	if(pAmountRepository.findById(pAmount.getId())==null)
    		return new ResponseEntity<>("This product does not exist",HttpStatus.NOT_FOUND);
    	this.orderService.updateProduct(order.getId(), pAmount.getId(), pAmount.getAmount());
		//pAmount.setTotal(pAmount.getAmount(), pAmount.getProduct().getPrice());
		//pAmountRepository.saveAndFlush(pAmount);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/order/{id}/confirmation", method = RequestMethod.PUT)
	public ResponseEntity<?> confirmation(@RequestBody (required = false) List <Address> dirs, @PathVariable long id) throws Exception{
    	User user = userComponent.getLoggedUser();
    	Address billing_address = new Address();
    	Address delivery_address = new Address();
    	Order orderaux;
    	List<Object> response = new ArrayList<>();
    	
    	if(user!=null)
			user = userRepository.findByName(user.getName());
    	
    	if(this.orderRepository.findById(id)==null)
			return new ResponseEntity<>("This product does not exist",HttpStatus.NOT_FOUND);
    	
    	if((this.orderRepository.findById(id).getUser()==null && user!=null) || (this.orderRepository.findById(id)!=null && user==null && this.orderRepository.findById(id).getUser()!=null))
			return new ResponseEntity<>("This user does not contains this order",HttpStatus.FORBIDDEN);
    	
    	if(user == null)
    		return new ResponseEntity<>("This user does not exist",HttpStatus.FORBIDDEN);
    	
    	orderaux = orderRepository.findById(id);
    	if(user!=null && orderaux.getUser().getId()!=user.getId())
			return new ResponseEntity<>("This user does not contains this order",HttpStatus.FORBIDDEN);
    	
    	if(orderaux.getStatus()!=null)
			return new ResponseEntity<>("This order is already completed",HttpStatus.FORBIDDEN);
    	
    	if(orderaux.getProductList().isEmpty())
    		return new ResponseEntity<>("This order is empty",HttpStatus.FORBIDDEN);
    	
    	if(dirs == null) {
    		billing_address = user.getDelivery();
    		delivery_address = user.getDelivery();
    	} else {
    		billing_address = new Address(dirs.get(1));
    		delivery_address = new Address(dirs.get(0));
    	}
    	if(billing_address == null || delivery_address == null) {
    		return new ResponseEntity<>("Addres not found",HttpStatus.NOT_FOUND);
    	}
    	//Order order1 = orderRepository.findById(id);
    	Order order = this.orderService.confirmOrder(delivery_address, billing_address, delivery_address.getName());
    	this.orderService.sendEmail(user, order, billing_address);
    	OrderMetrics lastMetrics = this.orderMetricsRepository.findAll().get(this.orderMetricsRepository.findAll().size()-1);
    	OrderMetrics orderMetrics = new OrderMetrics(lastMetrics);
    	orderMetrics.newOrder(order);
    	this.orderMetricsRepository.save(orderMetrics);
    	this.orderService.buildOrders();
    	response.add(order);
    	response.add(delivery_address);
    	response.add(billing_address);
    	return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/new/api/order")
	void ordersG(HttpServletResponse response) throws IOException {
	  response.sendRedirect("/api/order");
	}

	@GetMapping("/new/api/order/{id}")
	void orderG(HttpServletResponse response) throws IOException {
	  response.sendRedirect("/api/order/{id}");
	}

	@DeleteMapping("/new/api/order/{id}")
	void orderProductDel(HttpServletResponse response) throws IOException {
	  response.sendRedirect("/api/order/{id}");
	}

	@DeleteMapping("/new/api/order/{id}/{pid}")
	void orderProductAuxDel(HttpServletResponse response) throws IOException {
	  response.sendRedirect("/api/order/{id}/{pid}");
	}

	@PostMapping("/new/api/order/{id}")
	void orderProductPost(HttpServletResponse response) throws IOException {
	  response.sendRedirect("/api/order/{id}");
	}

	@PutMapping("/new/api/order/{id}")
	void orderUpdateProduct(HttpServletResponse response) throws IOException {
	  response.sendRedirect("/api/order/{id}");
	}

	@PutMapping("/new/api/order/{id}/confirmation")
	void orderConfirmation(HttpServletResponse response) throws IOException {
	  response.sendRedirect("/api/order/{id}/confirmation");
	}

	
	/*@RequestMapping(value = "/confirmation", method = RequestMethod.POST)
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
	}*/

}
