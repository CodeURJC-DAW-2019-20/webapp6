package com.webapp.animeshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.webapp.animeshop.model.ProductAmount;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductAmountRepository extends JpaRepository<ProductAmount, Long>{
	
	ProductAmount findById(long id);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM product_amount WHERE product_id =:id",nativeQuery = true)
	void deleteProductAmount(@Param("id")long productId);
	/*@Query(value="SELECT * FROM 'products', 'product_amount' WHERE 'products.id' = 'product_amount.product_id'")
	ProductAmount findByProductId();
	
	/*(SELECT pa FROM ProductAmount pa) UNION (SELECT p FROM Product p) WHERE p.id=pa.product.id"*/
	/*@Query(value="SELECT pa, p FROM ProductAmount pa JOIN pa.product p")*/

}
