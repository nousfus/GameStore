package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.GameRequirements;

public interface GameRequirementsDao extends JpaRepository<GameRequirements, String>{
//	List<GameRequirements> findAll(); 
//	GameRequirements findById(String id); 
//	void create(GameRequirements gr); 
//	void update(GameRequirements gr); 
//	void delete(String id);
	GameRequirements findByGameid(String id);
}
