package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.Reviews;

public interface ReviewsDao extends JpaRepository<Reviews, String>{
//	List<Reviews> findAll(); 
//	Reviews findById(String id); 
//	void create(Reviews r); 
//	void update(Reviews r); 
//	void delete(String id);
	List<Reviews> findByGameid(String gameid);
}
