package com.example.gamestore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.DeveloperProfiles;

public interface DeveloperProfilesDao extends JpaRepository<DeveloperProfiles, String>{
//	List<DeveloperProfiles> findAll(); 
//	DeveloperProfiles findById(String id); 
//	void create(DeveloperProfiles d); 
//	void update(DeveloperProfiles d); 
//	void delete(String id);
	DeveloperProfiles findByUsername(String username);
}
