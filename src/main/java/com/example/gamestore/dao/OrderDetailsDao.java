package com.example.gamestore.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.OrderDetails;

public interface OrderDetailsDao extends JpaRepository<OrderDetails, String>{
//	List<OrderDetails> findAll(); 
//	OrderDetails findById(String id); 
//	void create(OrderDetails od); 
//	void update(OrderDetails od); 
//	void delete(String id);
}
