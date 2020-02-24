package com.webapp.animeshop.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.animeshop.product.ProductAmount;
import com.webapp.animeshop.product.ProductAmountRepository;

@RestController
@RequestMapping("/api/product")
public class OrderRestController {
	
	@Autowired
	private ProductAmountRepository pAmountRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<ProductAmount> findProducts() {
		return pAmountRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteItem(@PathVariable long id) {
		pAmountRepository.deleteById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ProductAmount>updateItem(@PathVariable long id,@RequestBody ProductAmount pAmount, @PathVariable int qt) {
		pAmount.setAmount(qt);
		pAmount.setTotal(qt, pAmount.getProduct().getPrice());
		ProductAmount productAmount = pAmountRepository.saveAndFlush(pAmount);
		return new ResponseEntity<>(productAmount,HttpStatus.CREATED);
	}

}
