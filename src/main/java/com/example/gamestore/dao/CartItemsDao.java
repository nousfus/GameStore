package com.example.gamestore.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.CartItems;

public interface CartItemsDao extends JpaRepository<CartItems, String> {
//	List<CartItems> findAll(); 
//	CartItems findById(String id); 
//	void create(CartItems ci); 
//	void update(CartItems ci); 
//	void delete(String id);
}
