package com.example.gamestore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gamestore.entity.Roles;
import com.example.gamestore.entity.UserRoles;
import com.example.gamestore.service.UserRoleId;

public interface UserRolesDao extends JpaRepository<UserRoles, UserRoleId>{
//	List<UserRoles> findAll(); 
//	void create(UserRoles ur); 
//	void delete(String username, String roleId);
	@Query("SELECT r FROM Roles r WHERE r.roleId IN (SELECT u.role_id FROM UserRoles u WHERE u.username = :username)")
	List<Roles> findByUsername(@Param("username") String username);
}
