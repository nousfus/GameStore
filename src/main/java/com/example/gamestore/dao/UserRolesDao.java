package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.UserRoles;

public interface UserRolesDao extends JpaRepository<UserRoles, String>{
//	List<UserRoles> findAll(); 
//	void create(UserRoles ur); 
//	void delete(String username, String roleId);
}
