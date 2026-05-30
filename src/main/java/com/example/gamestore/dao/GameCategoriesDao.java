package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.GameCategories;

public interface GameCategoriesDao extends JpaRepository<GameCategories, String>{
//	List<GameCategories> findAll(); 
//	void create(GameCategories gc); 
//	void delete(String gameId, String categoryId);
}
