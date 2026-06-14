package com.example.gamestore.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.CartItems;
import com.example.gamestore.entity.Game;

public interface CartItemsDao extends JpaRepository<CartItems, String> {
//	List<CartItems> findAll(); 
//	CartItems findById(String id); 
//	void create(CartItems ci); 
//	void update(CartItems ci); 
//	void delete(String id);
	List<CartItems> findByCartId(String cartId);
	CartItems findByGame(Game game);
}
