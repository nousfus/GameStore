package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.Orders;

public interface OrdersDao extends JpaRepository<Orders, String>{
//	List<Orders> findAll(); 
//	Orders findById(String id); 
//	void create(Orders o); 
//	void update(Orders o); 
//	void delete(String id);
}
