package com.webapp.animeshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.animeshop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Product findByName(String name);

}
