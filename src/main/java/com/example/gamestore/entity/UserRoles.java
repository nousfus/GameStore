package com.example.gamestore.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserRoles")
public class UserRoles {
	@Id
	private String username; 
	private String role_id; 
	public UserRoles() { } 
	public UserRoles(String username, String roleId) { 
		this.username = username; 
		this.role_id = roleId; 
		}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRoleId() {
		return role_id;
	}
	public void setRoleId(String roleId) {
		this.role_id = roleId;
	}
	
}
