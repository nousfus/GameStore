package com.example.gamestore.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Roles")
public class Roles { 
	@Id
	private String role_id; 
	@Column(name = "role_name")
	private String roleName; 
	public Roles() { } 
	public Roles(String role_id, String role_name) { 
		this.role_id = role_id; 
		this.roleName = role_name; 
	}
	public String getrole_id() {
		return role_id;
	}
	public void setrole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getrole_name() {
		return roleName;
	}
	public void setrole_name(String role_name) {
		this.roleName = role_name;
	}
	
}