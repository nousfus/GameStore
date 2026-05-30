package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.Library;

public interface LibraryDao extends JpaRepository<Library, String> {
//	List<Library> findAll(); 
//	Library findById(String id); 
//	void create(Library l); 
//	void delete(String id);
}
