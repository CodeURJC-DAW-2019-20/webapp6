package com.webapp.animeshop.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping()
	public List<Product> showProducts() {
		return productRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Product showProduct(@PathVariable long id) {
		return productRepository.findById(id);
	}
	
	@PostMapping()
	public List<Product> addProduct(@RequestBody Product p) {
		Product product = p;
		productRepository.save(product);
		return this.showProducts();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<List<Product>> deleteProduct(@PathVariable long id) {
		Product product = productRepository.findById(id);
		if(product!=null) {
			productRepository.deleteById(id);
			return new ResponseEntity<>(productRepository.findAll(),HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
