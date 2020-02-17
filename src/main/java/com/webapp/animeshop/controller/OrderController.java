package com.webapp.animeshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webapp.animeshop.model.Order;
import com.webapp.animeshop.model.Product;
import com.webapp.animeshop.model.ProductAmount;
import com.webapp.animeshop.model.User;
import com.webapp.animeshop.repositories.OrderRepository;
import com.webapp.animeshop.repositories.ProductRepository;
import com.webapp.animeshop.repositories.UserRepository;
import com.webapp.animeshop.service.OrderService;

@Controller
public class OrderController{
	
	@Autowired
    private OrderService orderService;
	
	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;
    
    @RequestMapping("/shoppingCart")
	public String shoppingCart(Model model) {
    	long id = 18;
		//List<Product> products = this.orderRepository.findById(id).getProductList();
		Order order = this.orderRepository.findById(id);
		model.addAttribute("order", order);
		return "/cart";
	}
    
    @PostMapping(value="/addFromProduct/{id}")
    public String addProductToCart2(Model model, @PathVariable("id") long id, @RequestParam("qt") int qt) {
    	long oId = 18;
		this.orderService.addProductToOrder(oId, id, qt);
		return this.shoppingCart(model);
    }

    @PostMapping("/removeCartProduct/{id}")
    public String removeProductFromCart(Model model, @PathVariable("id") long id) {
        long oId = 19;
        this.orderService.deleteProductFromOrder(oId, id);
        return this.shoppingCart(model);
    }/*

    @PostMapping("/shoppingCart/checkout/{address}")
    public String checkout(@PathVariable("address") String address) {
    	List<Product> p = new ArrayList<>();
    	List<Menu> m = new ArrayList<>();
    	
    	for (ProductAmount pa: shoppingCartService.getProductsInShoppingCart()) {
    		for (int i = 0; i < pa.getAmount(); i++) {
    			p.add(pa.getProduct());
    		}
    	}
    	for (MenuAmount ma: shoppingCartService.getMenuInShoppingCart()) {
    		for (int i = 0; i < ma.getAmount(); i++) {
    			m.add(ma.getProduct());
    		}
    	}
    	
    	shoppingCartService.checkout(new ShoppingCart(address, p, m));
        return "/shoppingCart";
    }*/
	

}