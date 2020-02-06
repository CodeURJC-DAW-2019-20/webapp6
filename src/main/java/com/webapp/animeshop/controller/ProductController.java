package com.webapp.animeshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.animeshop.model.Product;
import com.webapp.animeshop.service.ProductService;

@RestController
@RequestMapping("/api/category")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/category")
	public String showProducts(Model model) {
		model.addAttribute("product", this.productService.getProducts());
		return "/category";
	}
	
	@RequestMapping("/category/addProduct")
	public String addProduct(Model model, @RequestParam String name) {
		Product product = new Product();
		this.productService.addProduct(product);
		return this.showProducts(model);
	}
	
}
