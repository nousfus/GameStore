package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.Notifications;

public interface NotificationsDao extends JpaRepository<Notifications, String> {
//	List<Notifications> findAll(); 
//	Notifications findById(String id); 
//	void create(Notifications n); 
//	void update(Notifications n); 
//	void delete(String id);
	List<Notifications> findByUsername(String username);
}
