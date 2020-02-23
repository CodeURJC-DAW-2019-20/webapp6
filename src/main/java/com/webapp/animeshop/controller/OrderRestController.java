package com.webapp.animeshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.animeshop.repositories.ProductAmountRepository;

@RestController
@RequestMapping("/api/product")
public class OrderRestController {
	
	@Autowired
	private ProductAmountRepository pAmountRepository;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable long id) {
		pAmountRepository.deleteById(id);
	}

}
