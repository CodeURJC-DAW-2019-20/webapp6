package com.webapp.animeshop.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.animeshop.blog.BlogRepository;
import com.webapp.animeshop.order.Order;
import com.webapp.animeshop.order.OrderRepository;
import com.webapp.animeshop.user.User;
import com.webapp.animeshop.user.UserComponent;

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
	private UserComponent userSession;
	
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
		return this.productRepository.search(key);
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
	
	public String showRecommendations() {
		HashMap<String, Integer> mostOrdered = new HashMap<>();
		Integer max = 0;
		String maxFranchise = "";
		User user = userSession.getLoggedUser();
		List<Order> orderList;
		if (user == null) {
			List<Product> productList = productRepository.findAll();
			Integer rand = (int) (Math.random() * productList.size());
			maxFranchise = productList.get(rand).getFranchise();
			//maxFranchise = "Naruto";
		} else {
			orderList = (List<Order>) user.getOrderList();
			if(orderList.get(orderList.size() - 1).getProductList().isEmpty()) {
				List<Product> productList = productRepository.findAll();
				Integer rand = (int) (Math.random() * productList.size());
				maxFranchise = productList.get(rand).getFranchise();
				return maxFranchise;
			}
			for (Order order : orderList) {
				List<ProductAmount> productList = order.getProductList();
				for (ProductAmount po : productList) {
					String franchise = po.getProduct().getFranchise();
					Integer aux = mostOrdered.get(franchise);
					if (aux == null) {
						mostOrdered.put(franchise, po.getAmount());
					} else {
						mostOrdered.put(franchise, aux + po.getAmount());
					}
					if (mostOrdered.get(franchise) > max) {
						max = mostOrdered.get(franchise);
						maxFranchise = franchise;
					}
				}
			}
		}
		return maxFranchise;

		// maxFranchise is the most purchased franchise
	}
	
	public List<Product> initializeFilters(String franchise, String distributor,
			Integer width,  Integer height, Integer min_price, Integer max_price) {
		if(franchise == null)
			franchise = "Cualquiera";
		if(distributor == null)
			distributor = "cualquiera";
		if(width == null)
			width = 150;
		if(height == null)
			height = 150;
		if(min_price == null) 
			min_price = 0;
		if(max_price == null)
			max_price = 5000;
		List <Product> list = this.filterProducts(franchise, distributor, width, height, min_price, max_price);
		return list;
	}
	
	public Page<Product> findAll (Pageable page){
		return productRepository.findAll(page);
	}
	
	public Page<Product> searchV2(Pageable page, String key){
		return productRepository.search(page, key);
	}

}