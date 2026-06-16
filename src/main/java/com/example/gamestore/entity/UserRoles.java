package com.example.gamestore.entity;
import com.example.gamestore.service.UserRoleId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserRoles")
@IdClass(UserRoleId.class)
public class UserRoles {
	@Id
    private String username;

    @Id
    private String role_id;
	    
	public UserRoles() { }
	public UserRoles(String username, String role_id) {
		super();
		this.username = username;
		this.role_id = role_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	} 
		
}
