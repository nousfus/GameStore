package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.GameVideos;

public interface GameVideosDao extends JpaRepository<GameVideos, String>{
//	List<GameVideos> findAll(); 
//	GameVideos findById(String id); 
//	void create(GameVideos gv); 
//	void update(GameVideos gv); 
//	void delete(String id);
}
