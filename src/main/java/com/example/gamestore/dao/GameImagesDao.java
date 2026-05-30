package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.GameImages;

public interface GameImagesDao extends JpaRepository<GameImages, String>{
//	List<GameImages> findAll(); 
//	GameImages findById(String id); 
//	void create(GameImages gi); 
//	void update(GameImages gi); 
//	void delete(String id);
}
