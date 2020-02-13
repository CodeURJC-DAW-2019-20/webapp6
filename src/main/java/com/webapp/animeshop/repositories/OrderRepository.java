package com.webapp.animeshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.animeshop.model.Order;
import com.webapp.animeshop.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

	Order findById(long Id);
	Order findByUser(User user);//User Name
	List<Order> findAll();
	
}
