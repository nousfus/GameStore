package com.example.gamestore.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.example.gamestore.entity.GameImages;

public interface GameImagesDao extends JpaRepository<GameImages, String>{
//	List<GameImages> findAll(); 
//	GameImages findById(String id); 
//	void create(GameImages gi); 
//	void update(GameImages gi); 
//	void delete(String id);
	@Query("SELECT g FROM GameImages g WHERE g.gameId = :id")
	List<GameImages> findByGameid(String id);
}
