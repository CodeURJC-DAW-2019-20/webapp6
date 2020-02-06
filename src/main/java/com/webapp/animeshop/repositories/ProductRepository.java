package com.webapp.animeshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.animeshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Product findById(long id);
	Product findByName(String name);
	List<Product> findByFranchise(String franchise);
	List<Product> findByDistributor(String distributor);
	List<Product> findAll();

}
