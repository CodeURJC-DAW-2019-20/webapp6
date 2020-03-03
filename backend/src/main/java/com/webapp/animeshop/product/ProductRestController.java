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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
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
	
	@GetMapping("/sortBy")
	public ResponseEntity<List<Product>> sortByProducts(@RequestParam String key) {
		switch(key) {
		case "desc":
			return new ResponseEntity<>(this.productRepository.findByPriceDesc(),HttpStatus.OK);
		case "asc":
			return new ResponseEntity<>(this.productRepository.findByPriceAsc(),HttpStatus.OK);
		default:
			return new ResponseEntity<>(productRepository.findAll(),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/franchises")
	public List<Product> franchisesProducts(@RequestParam String franchise) {
		return productRepository.findByFranchise(franchise);
	}
	
	@GetMapping("/search")
	public List<Product> searchProduct(@RequestParam String key) {
		return this.productService.search(key);
	}

}
