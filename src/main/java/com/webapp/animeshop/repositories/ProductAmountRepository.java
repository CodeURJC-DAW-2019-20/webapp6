package com.webapp.animeshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.animeshop.model.ProductAmount;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAmountRepository extends JpaRepository<ProductAmount, Long>{
	
	ProductAmount findById(long id);

}
