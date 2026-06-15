package com.example.gamestore.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Roles")
public class Roles { 
	@Id
	private String role_id; 
	@Column(name = "role_name")
	private String rolename; 
	@Override
	public String toString() {
		return role_id + ", " + rolename;
	}
	public Roles() { } 
	public Roles(String role_id, String role_name) { 
		this.role_id = role_id; 
		this.rolename = role_name; 
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String roleName) {
		this.rolename = roleName;
	}
	
	
}