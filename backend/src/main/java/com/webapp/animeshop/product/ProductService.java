package com.webapp.animeshop.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.animeshop.blog.BlogRepository;
import com.webapp.animeshop.order.Order;
import com.webapp.animeshop.order.OrderRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductAmountRepository pAmountRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
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
		List<Order> orders = this.orderRepository.findAll();
		this.pAmountRepository.deleteProductAmount(productId);
		this.blogRepository.deleteBlog(productId);
		//preguntar a mica por: si borro un elemento y se ha realizado un pedido donde se ha comprado ese producto,
		//¿como puedo acceder a ese elemento si ya no tengo la relaccion en la base de datos?
		for(int i =0;i<orders.size();i++) {
			if(!orders.get(i).getProductList().isEmpty())
				for(int j=0;j<orders.get(i).getProductList().size();j++) {
					if(orders.get(i).getProductList().get(j).getProduct().getName().equals(product.getName())) {	
						orders.get(i).getProductList().remove(j);
					}
				}
		}
		this.orderRepository.saveAll(orders);
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
	
	public HashMap<String, Integer> nProductsByFranchise(){
		HashMap<String, Integer> nByFranchise = new HashMap<>();
		List<String> franchises = this.productRepository.findFranchises();
		for(int i = 0;i<franchises.size();i++) {
			nByFranchise.put(franchises.get(i),this.productRepository.findFranchiseNumber(franchises.get(i)));
		}
		return nByFranchise;
	}
	
	public HashMap<String, Integer> nProductsByDistributor(){
		HashMap<String, Integer> nByDistributor = new HashMap<>();
		List<String> distributors = this.productRepository.findDistributors();
		for(int i = 0;i<distributors.size();i++) {
			nByDistributor.put(distributors.get(i),this.productRepository.findDistributorNumber(distributors.get(i)));
		}
		return nByDistributor;
	}

}