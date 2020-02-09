package com.webapp.animeshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.animeshop.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

	Blog findById(long id);
	Blog findByName(String name);
	List<Blog> findAll();
}
