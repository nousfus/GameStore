package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.mapping.JpaPersistentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.Cart;

public interface CartDao extends JpaRepository<Cart, String> {
//	List<Cart> findAll(); 
//	Cart findById(String id); 
//	void create(Cart c); 
//	void update(Cart c); 
//	void delete(String id);
	Cart findByUsername(String username);
}
