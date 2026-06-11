package com.example.gamestore.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gamestore.entity.OrderDetails;

public interface OrderDetailsDao extends JpaRepository<OrderDetails, String>{
//	List<OrderDetails> findAll(); 
//	OrderDetails findById(String id); 
//	void create(OrderDetails od); 
//	void update(OrderDetails od); 
//	void delete(String id);
	@Query("select o from OrderDetails o where o.order.order_id = :id")
	List<OrderDetails> findByOrderID(@Param("id") String id);
}
	