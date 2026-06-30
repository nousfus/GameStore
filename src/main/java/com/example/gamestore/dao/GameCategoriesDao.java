package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gamestore.entity.GameCategories;
import com.example.gamestore.service.GameCategoriesId;

import jakarta.transaction.Transactional;
public interface GameCategoriesDao extends JpaRepository<GameCategories, GameCategoriesId>{
//	List<GameCategories> findAll(); 
//	void create(GameCategories gc); 
//	void delete(String gameId, String categoryId);
	List<GameCategories> findByGameid(String gameid);
	@Modifying
	@Transactional
	@Query("DELETE FROM GameCategories g WHERE g.gameid = :gameid")
	void deleteByGameid(@Param("gameid") String gameid);
}
