package com.webapp.animeshop.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Metrics;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.animeshop.product.ProductAmount;
import com.webapp.animeshop.product.ProductAmountRepository;
import com.webapp.animeshop.product.ProductRepository;
import com.webapp.animeshop.product.ProductService;
import com.webapp.animeshop.user.Address;
import com.webapp.animeshop.user.User;
import com.webapp.animeshop.user.UserComponent;
import com.webapp.animeshop.user.UserRepository;

@RestController
@RequestMapping("/api/metrics")
public class MetricsRestController {
	
	@Autowired
	private OrderMetricsRepository orderMetricsRepository;
	
	@GetMapping("/{id}")
	public OrderMetrics getMetrics(@PathVariable long id) {
		return orderMetricsRepository.findById(id);
	}
	
	/*@PostMapping()
	public OrderMetrics updateMetrics(@RequestBody OrderMetrics om) {
		OrderMetrics orderMetrics = om;
		orderMetricsRepository.save(orderMetrics);
		return orderMetrics;
	} */
	
	@GetMapping()
	public OrderMetrics getLastMetric() {
		List<OrderMetrics> list = orderMetricsRepository.findAll();
		OrderMetrics om = list.get(list.size() - 1);
		return om;
	}
	
	@GetMapping("/all")
	public List<OrderMetrics> getAllMetrics() {
		return orderMetricsRepository.findAll();
	}

}
