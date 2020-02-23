package com.webapp.animeshop.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webapp.animeshop.user.User;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{

	Order findById(long Id);
	Order findByUser(User user);//User Name
	List<Order> findAll();
	@Query(value="SELECT * FROM orders WHERE user_id =:id and status is NULL",nativeQuery = true)
	Order findByStatus(@Param("id")long userId);
	@Query(value="SELECT * FROM orders WHERE user_id is NULL",nativeQuery = true)
	Order findNotRelated();
	
}
