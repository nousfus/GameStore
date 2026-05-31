package com.example.gamestore.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.Users;


public interface UserDao extends JpaRepository<Users, String> {
//	List<Users> findAll();
//	Users findById(String username);
//	void create(Users user);
//	void update(Users user);
//	void delete(String username);
//	Users login(String username, String password);
	Users findByEmail(String email);
}
