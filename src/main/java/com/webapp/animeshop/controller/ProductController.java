package com.webapp.animeshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.animeshop.model.Product;
import com.webapp.animeshop.repositories.ProductRepository;
import com.webapp.animeshop.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping("/products")
	public String showProducts(Model model) {
		model.addAttribute("products", this.productService.getProducts().subList(0, Math.min(productRepository.findAll().size(),10)));
		return "/category";
	}
	
	@RequestMapping("/product/{id}")
	public String showProduct(Model model, @PathVariable long id) {
		Product product = productService.getProduct(id);
		model.addAttribute("product", product);
		return "/singleProduct";
	}
	
	@RequestMapping("/addProduct")
	public String addProduct(Model model, @RequestParam String name, @RequestParam String franchise, @RequestParam String price,
							 @RequestParam String description, @RequestParam int width, @RequestParam int height,
							 @RequestParam int weight, @RequestParam String distributor, @RequestParam String reference
							 ) {
		Product product = new Product(name,franchise,Double.parseDouble(price),description,width,height,weight,distributor,reference,"","");
		this.productService.addProduct(product);
		return this.showProducts(model);
	}
	
	@RequestMapping("/deleteProduct/{id}")
	public String deleteProduct(Model model, @PathVariable long id) {
		productService.deleteProduct(id);
		return this.showProducts(model);
	}
	
	@RequestMapping("/product/deleteProduct/{id}")
	public String deleteInProduct(Model model, @PathVariable long id) {
		productService.deleteProduct(id);
		return this.showProducts(model);
	}
	
	@RequestMapping("/filterProduct")
	public String filterProducts(Model model, @RequestParam String franchise, @RequestParam String distributor,
								 @RequestParam int width, @RequestParam int height,
								 @RequestParam int min_price, @RequestParam int max_price
								 ) {
		List<Product> products = this.productService.filterProducts(franchise, distributor, width, height, min_price, max_price);
		model.addAttribute("products", products);
		return "/category";
	}
	
	@RequestMapping("/upperToLower")
	public String upperToLower(Model model) {
		List<Product> products = this.productService.upperToLower();
		model.addAttribute("products", products);
		return "/category";
	}
	
	@RequestMapping("/lowerToUpper")
	public String lowerToUpper(Model model) {
		List<Product> products = this.productService.lowerToUpper();
		model.addAttribute("products", products);
		return "/category";
	}
	
	@RequestMapping("/search")
	public String search(Model model, @RequestParam String key) {
		List<Product> products = this.productService.search(key);
		model.addAttribute("products", products);
		return "/category";
	}
	
}

