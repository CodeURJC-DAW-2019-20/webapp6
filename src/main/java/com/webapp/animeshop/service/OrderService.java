package com.webapp.animeshop.service;

import com.webapp.animeshop.model.Address;
import com.webapp.animeshop.model.Order;
import com.webapp.animeshop.model.Product;
import com.webapp.animeshop.model.ProductAmount;
import com.webapp.animeshop.model.User;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webapp.animeshop.repositories.OrderRepository;
import com.webapp.animeshop.repositories.ProductAmountRepository;
import com.webapp.animeshop.repositories.ProductRepository;
import com.webapp.animeshop.repositories.UserRepository;
import com.webapp.animeshop.user.UserComponent;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductAmountRepository pAmountRepository;
	
	@Autowired
	private UserComponent userSession;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private JavaMailSender sender;
	
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public Order getOrder(long orderId) {
		Order order = this.orderRepository.findById(orderId);
		if(order!=null) {
			return order;
		}
		return null;
	}
	
	public Order getOrderByUserName(User orderUser) {
		Order order = this.orderRepository.findByUser(orderUser);
		if(order!=null) {
			return order;
		}
		return null;
	}
	
	public List<Order> getOrders(){
		return this.orderRepository.findAll();
	}
	
	public Order addOrder(Order order) {
		return this.orderRepository.save(order);
	}
	
	public void deleteOrder(long orderId) {
		Order order = this.orderRepository.findById(orderId);
		if(order!=null) {
			this.orderRepository.delete(order);
		}
	}
	
	public void addProductToOrder(long orderId, long productId, int qt) {
		boolean esta = false;
		int i = 0;
		Order order = this.orderRepository.findById(orderId);
		Product product = this.productRepository.findById(productId);
		//order.getProductList().add(pAmount);
		while(i<order.getProductList().size() & esta!=true) {
			if(order.getProductList().get(i).getProduct().getName().equals(product.getName())) {
				order.getProductList().get(i).setAmount(order.getProductList().get(i).getAmount()+qt);
				order.getProductList().get(i).setTotal(order.getProductList().get(i).getAmount(), product.getPrice());
				order.setTotal();
				esta = true;
			}
			i+=1;
		}
		if(esta==false) {
			order.getProductList().add(new ProductAmount(product,order,qt));
			order.setTotal();
		}
		this.addOrder(order);
		/*if(!order.getProductList().contains(pAmount)) {
			ProductAmount productAmount = new ProductAmount(product,order,qt);
			this.pAmountRepository.save(productAmount);
			order.getProductList().add(productAmount);
			this.addOrder(order);
		}
		else {
			pAmount.setAmount(pAmount.getAmount()+qt);
			this.pAmountRepository.save(pAmount);
			/*ProductAmount pAmountAux = this.pAmountRepository.findByProductId(productId);
			pAmountAux.setAmount(pAmountAux.getAmount()+qt);
			this.pAmountRepository.save(pAmountAux);
			order.getProductList().remove(pAmount);
			order.getProductList().add(pAmountAux);
			this.orderRepository.save(order);
		}*/
	}
	
	public void deleteProductFromOrder(long orderId, long productId) {
		Order order = this.orderRepository.findById(orderId);
		ProductAmount product = this.pAmountRepository.findById(productId);
		order.getProductList().remove(product);
		order.setTotal();
		this.pAmountRepository.delete(product);
		this.addOrder(order);
	}
	
	public Order confirmOrder(Address delivery_address, Address billing_address) {
		User user = userSession.getLoggedUser();
    	user.setDelivery(delivery_address);
    	this.userRepository.save(user);
    	Order order = this.orderRepository.findByStatus(user.getId());
    	order.setStatus("Complete");
    	this.orderRepository.save(order);
    	user.getOrderList().add(order);
    	this.userRepository.save(user);
    	Order newOrder = new Order();
    	newOrder.setUser(user);
    	this.orderRepository.save(newOrder);
    	return order;
	}
    
    public void sendEmail(User userInfo, Order orderInfo, Address billing) throws Exception{
    	String info = "\nDirección de envío:" + userInfo.getDelivery().toString() + "\nDirección de facturación:" + 
    				billing.toString() + orderInfo.toString();
    	String email = userInfo.getDelivery().getEmail();
    	
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        ClassPathResource file = new ClassPathResource("/static/img/shop-logo.png");
        helper.addAttachment("/static/img/shop-logo.png", file);
        helper.setTo(email);
        helper.setText(info);
        helper.setSubject("Factura de su pedido " + Long.toString(orderInfo.getId()));
         
        sender.send(message);
    }
	
}
