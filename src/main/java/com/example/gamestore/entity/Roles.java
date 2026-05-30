package com.example.gamestore.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Roles")
public class Roles { 
	@Id
	private String role_id; 
	private String role_name; 
	public Roles() { } 
	public Roles(String role_id, String role_name) { 
		this.role_id = role_id; 
		this.role_name = role_name; 
	}
	public String getrole_id() {
		return role_id;
	}
	public void setrole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getrole_name() {
		return role_name;
	}
	public void setrole_name(String role_name) {
		this.role_name = role_name;
	}
	
}