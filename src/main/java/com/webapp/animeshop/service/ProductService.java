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
	
	public List<Product> filterProducts(String franchise, String distributor, int width, int height, int min_price, int max_price){
		List<Product> products = this.productRepository.findAll();
		List<Product> aux = new ArrayList<Product>();
		for (int i = 0; i < products.size(); i++) {
			if((franchise.equals("Cualquiera")||products.get(i).getFranchise().equals(franchise))
				&(distributor.equals("Cualquiera")||products.get(i).getDistributor().equals(distributor))
				&((products.get(i).getPrice()>=min_price) & (products.get(i).getPrice()<=max_price))
				&((products.get(i).getWidth()<width)&(products.get(i).getHeight()<height)))
				
				aux.add(products.get(i));
		}
		return aux;
	}
	
	public List<Product> upperToLower(){
		List<Product> products = this.productRepository.findAll();
		products.sort((o1,o2) -> o1.compareTo(o2));
		return products;
	}
	
	public List<Product> lowerToUpper(){
		List<Product> products = this.productRepository.findAll();
		products.sort((o1,o2) -> o1.compareTo2(o2));
		return products;
	}
	
	public List<Product> search(String key){
		List<Product> products = this.productRepository.findAll();
		List<Product> aux = new ArrayList<Product>();
		for(int i = 0; i < products.size(); i++) {
			if(products.get(i).getName().toLowerCase().contains(key.toLowerCase()))
				aux.add(products.get(i));
		}
		return aux;
	}

}