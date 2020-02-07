package com.webapp.animeshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		model.addAttribute("products", this.productService.getProducts());
		return "/category";
	}
	
	@RequestMapping("/product/{id}")
	public String showProduct(Model model, @PathVariable long id) {
		Product product = productService.getProduct(id);
		model.addAttribute("product", product);
		return "/singleProduct";
	}
	
	@RequestMapping("/category/addProduct")
	public String addProduct(Model model, @RequestParam String name) {
		Product product = new Product();
		this.productService.addProduct(product);
		return this.showProducts(model);
	}
	
}
