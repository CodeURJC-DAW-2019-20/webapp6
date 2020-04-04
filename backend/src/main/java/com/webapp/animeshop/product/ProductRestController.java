package com.webapp.animeshop.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.animeshop.blog.BlogRepository;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	/*@GetMapping()
	public ResponseEntity<Page<Product>> showProducts(@PageableDefault (value=10) Pageable page, @RequestParam (required = false) String sort, @RequestParam (required = false) String category, 
				@RequestParam (required = false) String key) {
		if(sort!=null)
			switch(sort) {
			case "desc":
				return new ResponseEntity<>(this.productRepository.findByPriceDesc(page),HttpStatus.OK);
			case "asc":
				return new ResponseEntity<>(this.productRepository.findByPriceAsc(page),HttpStatus.OK);
			default:
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		if(category!=null) {
			if(this.productRepository.findByFranchise(category).isEmpty())
				return new ResponseEntity<>(productRepository.findAll(page),HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(productRepository.findByFranchise(page, category),HttpStatus.OK);
		}
		if(key!=null) {
			if(this.productService.search(key).isEmpty())
				return new ResponseEntity<>(productRepository.findAll(page),HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(productService.searchV2(page, key),HttpStatus.OK);
		}
		return new ResponseEntity<>(productRepository.findAll(page),HttpStatus.OK);
	}*/
	
	@GetMapping()
	public ResponseEntity<?> showProducts(@PageableDefault (value=10) Pageable page, @RequestParam (required = false) String toDo ,@RequestParam (required = false) String sort, @RequestParam (required = false) String category, 
				@RequestParam (required = false) String key, @RequestParam (required = false) String franchise, @RequestParam (required = false) String distributor,
				@RequestParam (required = false) Integer width, @RequestParam (required = false) Integer height, @RequestParam (required = false) Integer min_price,
				@RequestParam (required = false) Integer max_price) {
		if(toDo!=null)
			switch(toDo) {
			case "sort":
				switch(sort) {
				case "desc":
					return new ResponseEntity<>(this.productRepository.findByPriceDesc(page),HttpStatus.OK);
				case "asc":
					return new ResponseEntity<>(this.productRepository.findByPriceAsc(page),HttpStatus.OK);
				default:
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			case "search":
				if(key!=null) {
					if(this.productService.search(key).isEmpty())
						return new ResponseEntity<>(productRepository.findAll(page),HttpStatus.NOT_FOUND);
					return new ResponseEntity<>(productService.searchV2(page, key),HttpStatus.OK);
				}
			case "filter":
				List<Product> products = this.productService.initializeFilters(franchise, distributor, width, height, min_price,
						max_price);
				if(!products.isEmpty()) {
					return new ResponseEntity<>(products, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			case "recommendations":
				String franchise_aux = this.productService.showRecommendations();
				List<Product> auxList = this.productRepository.findByFranchise(franchise_aux);
				int cont = 5;
				LinkedList<Product> list = new LinkedList<>();
				while(!auxList.isEmpty() && cont > 0) {
					int rand = (int) (Math.random() * auxList.size());
					list.add(auxList.remove(rand));
					cont--;
				}
				return new ResponseEntity<>(list,HttpStatus.OK);
			default:
				return new ResponseEntity<>(productRepository.findAll(page).getContent(),HttpStatus.OK);
			}
		return new ResponseEntity<>(productRepository.findAll(page).getContent(),HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Product showProduct(@PathVariable long id) {
		return productRepository.findById(id);
	}
	
	@PostMapping()
	public Product addProduct(@RequestBody Product p) {
		Product product = p;
		productRepository.save(product);
		return product;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
		Product product = productRepository.findById(id);
		if(product!=null) {
			productService.deleteProduct(id);
			
			return new ResponseEntity<>(product,HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/*@GetMapping("/filter")
	public ResponseEntity<?> filterProducts(@RequestParam (required = false) String franchise, @RequestParam (required = false) String distributor,
			@RequestParam (required = false) Integer width, @RequestParam (required = false) Integer height, @RequestParam (required = false) Integer min_price,
			@RequestParam (required = false) Integer max_price) {
		List<Product> products = this.productService.initializeFilters(franchise, distributor, width, height, min_price,
				max_price);
		if(!products.isEmpty()) {
			return new ResponseEntity<>(products, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Products not found with these filters", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/recommendations")
	public List<Product> showRec(){
		String franchise = this.productService.showRecommendations();
		List<Product> auxList = this.productRepository.findByFranchise(franchise);
		int cont = 5;
		LinkedList<Product> list = new LinkedList<>();
		while(!auxList.isEmpty() && cont > 0) {
			int rand = (int) (Math.random() * auxList.size());
			list.add(auxList.remove(rand));
			cont--;
		}
		return list;
	}*/
}
