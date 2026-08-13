package com.example.gamestore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamestore.entity.Roles;

public interface RolesDao extends JpaRepository<Roles, String> {
//	List<Roles> findAll(); 
//	Roles findById(String roleId); 
//	void create(Roles role); 
//	void update(Roles role); 
//	void delete(String roleId);
	Roles findByRoleName(String name);
}
