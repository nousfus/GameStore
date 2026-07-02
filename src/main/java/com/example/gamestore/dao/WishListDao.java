package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.WishList;

public interface WishListDao extends JpaRepository<WishList, String>{
    //List<WishList> findAll(); 
    //WishList findById(String id); 
    //void create(WishList w); 
	//void delete(String id);
	List<WishList> findByUsername(String username);
}
