package com.example.gamestore.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.Categories;

public interface CategoriesDao extends JpaRepository<Categories, String>{
//	List<Categories> findAll(); 
//	Categories findById(String id); 
//	void create(Categories c); 
//	void update(Categories c); 
//	void delete(String id);
//	List<Categories> findByCateId(String id);
	
}
