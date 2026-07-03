package com.example.gamestore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.Payments;

public interface PaymentsDao extends JpaRepository<Payments, String>{
//	List<Payments> findAll(); 
//	Payments findById(String id); 
//	void create(Payments p); 
//	void update(Payments p); 
//	void delete(String id);
	Payments findByOrderid(String orderid);
}
