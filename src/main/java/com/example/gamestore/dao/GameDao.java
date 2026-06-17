package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.gamestore.entity.Game;

public interface GameDao extends JpaRepository<Game, String>{
//	List<Game> findAll(); 
//	Game findById(String id); 
//	void create(Game g); 
//	void update(Game g); 
//	void delete(String id); 
//	List<Game> searchByName(String keyword);
	List<Game> findTop3ByRating(int rating);
	@Query("SELECT g FROM Game g")
	Page<Game> findAllGame(Pageable pageable);
}
