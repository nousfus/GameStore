package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gamestore.entity.Orders;

public interface OrdersDao extends JpaRepository<Orders, String>{
//	List<Orders> findAll(); 
//	Orders findById(String id); 
//	void create(Orders o); 
//	void update(Orders o); 
//	void delete(String id);
	List<Orders> findByUsername(String username);
	@Query("select o from Orders o where o.username = :username and o.status = :status")
	List<Orders> findByUsernameAndStatus(@Param("username")String username,@Param("status")String status);
}
