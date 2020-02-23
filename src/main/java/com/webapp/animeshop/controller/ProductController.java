package com.webapp.animeshop.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.animeshop.model.Order;
import com.webapp.animeshop.model.Product;
import com.webapp.animeshop.model.ProductAmount;
import com.webapp.animeshop.model.User;
import com.webapp.animeshop.repositories.ProductRepository;
import com.webapp.animeshop.service.ProductService;
import com.webapp.animeshop.user.UserComponent;

@Controller
public class ProductController extends WebController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserComponent userSession;

	@Autowired
	private ProductRepository productRepository;

	private String showRecommendations() {
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
			if(orderList.isEmpty()) {
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

	@RequestMapping("/products")
	public String showProducts(Model model) {
		String franchise = showRecommendations();
		List<Product> auxList = productRepository.findByFranchise(franchise);
		model.addAttribute("products", this.productService.getProducts());
		
		int cont = 5;
		LinkedList<Product> list = new LinkedList<>();;
		while(!auxList.isEmpty()&&cont>0) {
			int rand = (int) (Math.random() * auxList.size());
			list.add(auxList.remove(rand));
			cont--;
			//Only takes 5 products from the recommended franchise
		}
		
		HashMap<String, Integer> nByFranchise = this.productService.nProductsByFranchise();
		HashMap<String, Integer> nByDistributor = this.productService.nProductsByDistributor();
		Integer total = this.productRepository.findProductAmount();
		model.addAttribute("franchises",nByFranchise);
		model.addAttribute("distributors",nByDistributor);
		model.addAttribute("total", total);
		model.addAttribute("list", list);

		return "/category";
	}

	@RequestMapping("/product/{id}")
	public String showProduct(Model model, @PathVariable long id) {
		Product product = productService.getProduct(id);
		model.addAttribute("product", product);
		return "/singleProduct";
	}

	@RequestMapping("/addProduct")
	public String addProduct(Model model, Product product) {
		product.setImage("/img/product/notavailable.png");
		product.setImagefull("/img/product/notavailable2.png");
		this.productService.addProduct(product);
		return this.showProducts(model);
	}

	@RequestMapping("/deleteProduct/{id}")
	public String deleteProduct(Model model, @PathVariable long id) {
		productService.deleteProduct(id);
		return this.showProducts(model);
	}

	@RequestMapping("/filterProduct")
	public String filterProducts(Model model, @RequestParam String franchise, @RequestParam String distributor,
			@RequestParam int width, @RequestParam int height, @RequestParam int min_price,
			@RequestParam int max_price) {
		List<Product> products = this.productService.filterProducts(franchise, distributor, width, height, min_price,
				max_price);
		HashMap<String, Integer> nByFranchise = this.productService.nProductsByFranchise();
		HashMap<String, Integer> nByDistributor = this.productService.nProductsByDistributor();
		Integer total = this.productRepository.findProductAmount();
		model.addAttribute("franchises",nByFranchise);
		model.addAttribute("distributors",nByDistributor);
		model.addAttribute("total", total);
		model.addAttribute("products", products);
		return "/category";
	}

	@RequestMapping("/sortBy")
	public String upperToLower(Model model,@RequestParam String value) {
		HashMap<String, Integer> nByFranchise = this.productService.nProductsByFranchise();
		HashMap<String, Integer> nByDistributor = this.productService.nProductsByDistributor();
		Integer total = this.productRepository.findProductAmount();
		model.addAttribute("franchises",nByFranchise);
		model.addAttribute("distributors",nByDistributor);
		model.addAttribute("total", total);
		switch(value) {
			case "Desc":
				model.addAttribute("products", this.productRepository.findByPriceDesc());
				break;
			case "Asc":
				model.addAttribute("products", this.productRepository.findByPriceAsc());
				break;
			default:
				model.addAttribute("products", this.productRepository.findAll());
				break;
		}
		return "/category";
	}

	@RequestMapping("/search")
	public String search(Model model, @RequestParam String key) {
		List<Product> products = this.productService.search(key);
		HashMap<String, Integer> nByFranchise = this.productService.nProductsByFranchise();
		HashMap<String, Integer> nByDistributor = this.productService.nProductsByDistributor();
		Integer total = this.productRepository.findProductAmount();
		model.addAttribute("franchises",nByFranchise);
		model.addAttribute("distributors",nByDistributor);
		model.addAttribute("total", total);
		model.addAttribute("products", products);
		return "/category";
	}
	
	@RequestMapping("/products/{franchise}")
	public String searchFranchise(Model model, @RequestParam String franchise) {
		List<Product> products = this.productRepository.findByFranchise(franchise);
		HashMap<String, Integer> nByFranchise = this.productService.nProductsByFranchise();
		HashMap<String, Integer> nByDistributor = this.productService.nProductsByDistributor();
		Integer total = this.productRepository.findProductAmount();
		model.addAttribute("franchises",nByFranchise);
		model.addAttribute("distributors",nByDistributor);
		model.addAttribute("total", total);
		model.addAttribute("products", products);
		return "/category";
	}

}
