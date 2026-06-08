package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.GameCategories;
import com.example.gamestore.entity.GameCategoriesId;

public interface GameCategoriesDao extends JpaRepository<GameCategories, GameCategoriesId>{
//	List<GameCategories> findAll(); 
//	void create(GameCategories gc); 
//	void delete(String gameId, String categoryId);
}
