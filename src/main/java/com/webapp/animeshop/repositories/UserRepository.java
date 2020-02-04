package com.webapp.animeshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webapp.animeshop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByName(String name);

}
