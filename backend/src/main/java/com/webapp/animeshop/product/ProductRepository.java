package com.webapp.animeshop.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Product findById(long id);
	Product findByName(String name);
	
	List<Product> findByFranchise(String franchise);
	List<Product> findByDistributor(String distributor);
	List<Product> findAll();
	
	@Query(value="SELECT DISTINCT franchise FROM products",nativeQuery = true)
	List<String> findFranchises();
	
	@Query(value="SELECT DISTINCT distributor FROM products",nativeQuery = true)
	List<String> findDistributors();
	
	@Query(value="SELECT COUNT(franchise) FROM products WHERE franchise = :fran",nativeQuery = true)
	Integer findFranchiseNumber(@Param("fran")String pfranchise);
	
	@Query(value="SELECT COUNT(distributor) FROM products WHERE distributor = :dis",nativeQuery = true)
	Integer findDistributorNumber(@Param("dis")String pdistributor);
	
	@Query(value="SELECT COUNT(id) FROM products",nativeQuery = true)
	Integer findProductAmount();
	
	@Query(value="SELECT * FROM products ORDER BY price DESC",nativeQuery = true)
	List<Product> findByPriceDesc();
	
	@Query(value="SELECT * FROM products ORDER BY price ASC",nativeQuery = true)
	List<Product> findByPriceAsc();
	
	@Query(value="SELECT * FROM products WHERE name LIKE CONCAT('%',:key,'%') ",nativeQuery = true)
	List<Product> search(String key);
	
	@Query(value="SELECT * FROM products ORDER BY price DESC",nativeQuery = true)
	Page<Product> findByPriceDesc(Pageable page);
	
	@Query(value="SELECT * FROM products ORDER BY price ASC",nativeQuery = true)
	Page<Product> findByPriceAsc(Pageable page);

	Page<Product> findByFranchise(Pageable page, String franchise);
	
	@Query(value="SELECT * FROM products WHERE name LIKE CONCAT('%',:key,'%') ",nativeQuery = true)
	Page<Product> search(Pageable page, String key);
	
}
