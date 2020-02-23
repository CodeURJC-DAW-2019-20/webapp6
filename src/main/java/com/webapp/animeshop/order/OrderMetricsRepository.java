package com.webapp.animeshop.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webapp.animeshop.product.ProductAmount;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderMetricsRepository extends JpaRepository<OrderMetrics, Long>{
	
	OrderMetrics findById(long id);
	List<OrderMetrics> findAll();

}