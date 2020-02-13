package com.webapp.animeshop.service;

import com.webapp.animeshop.model.Order;
import com.webapp.animeshop.model.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.animeshop.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
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
}
