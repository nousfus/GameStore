package com.example.gamestore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.Game;

public interface GameDao extends JpaRepository<Game, String>{
//	List<Game> findAll(); 
//	Game findById(String id); 
//	void create(Game g); 
//	void update(Game g); 
//	void delete(String id); 
//	List<Game> searchByName(String keyword);
}
