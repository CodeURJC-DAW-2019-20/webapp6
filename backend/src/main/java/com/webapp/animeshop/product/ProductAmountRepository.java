package com.webapp.animeshop.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.animeshop.order.Order;

@Repository
public interface ProductAmountRepository extends JpaRepository<ProductAmount, Long>{
	
	ProductAmount findById(long id);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM product_amount WHERE product_id =:id",nativeQuery = true)
	void deleteProductAmount(@Param("id")long productId);
	
	@Query(value="SELECT * FROM product_amount WHERE order_id =:id",nativeQuery = true)
	List<ProductAmount> findByOrderId(@Param("id")long orderId);

}
