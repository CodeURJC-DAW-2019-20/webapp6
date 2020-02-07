package com.webapp.animeshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.animeshop.model.Product;
import com.webapp.animeshop.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	public ProductService(ProductRepository productRepository) {
    	this.productRepository = productRepository;
    }

	public Product getProduct(long productId) {
		Product product = this.productRepository.findById(productId);
		if (product != null) {
			return product;
		}
		return null;
	}
	
	public Product getProductByName(String name) {
		Product product = this.productRepository.findByName(name);
		if (product != null) {
			return product;
		}
		return null;
	}
	
	public List<Product> getProducts(){
		return this.productRepository.findAll();
	}
	
	public List<Product> getProductsByFranchise(String franchise){
		List<Product> products = this.productRepository.findByFranchise(franchise);
		return products;
	}
	
	public List<Product> getProductsByDistributor(String distributor){
		List<Product> products = this.productRepository.findByDistributor(distributor);
		return products;
	}	
	
	/*The 2 previous methods in one
	public List<Product> getProductsByFilter(String filter, String key){
		List<Product> products = new ArrayList<Product>();
		
		switch(filter) {
			case "franchise":
				products = this.getProductsByFranchise(key);
				break;
			case "distributor":
				products = this.getProductsByDistributor(key);
				break;
		}
		
		return products;
	}*/
	
	public Product addProduct(Product product)  {
		return this.productRepository.save(product);
	}
	
	public void deleteProduct(long productId) {
		Product product = this.productRepository.findById(productId);
		if (product != null) {
			this.productRepository.delete(product);
		}
	}

}