package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.GameVersions;

public interface GameVersionsDao extends JpaRepository<GameVersions, String> {
//	List<GameVersions> findAll(); 
//	GameVersions findById(String id); 
//	void create(GameVersions gv); 
//	void update(GameVersions gv); 
//	void delete(String id);
}
