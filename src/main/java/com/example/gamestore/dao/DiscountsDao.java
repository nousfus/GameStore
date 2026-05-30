package com.example.gamestore.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.Discounts;

public interface DiscountsDao extends JpaRepository<Discounts, String> {
//	List<Discounts> findAll(); 
//	Discounts findById(String id); 
//	void create(Discounts d); 
//	void update(Discounts d); 
//	void delete(String id);
}
