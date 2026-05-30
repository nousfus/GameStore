package com.example.gamestore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DeveloperProfiles")
public class DeveloperProfiles {
	@Id
	private String developer_id; 
	private String username; 
	private String studio_name; 
	private String description; 
	private float revenue; 
	public DeveloperProfiles() { }
	public DeveloperProfiles(String developer_id, String username, String studio_name, String description,
			float revenue) {
		super();
		this.developer_id = developer_id;
		this.username = username;
		this.studio_name = studio_name;
		this.description = description;
		this.revenue = revenue;
	}
	public String getdeveloper_id() {
		return developer_id;
	}
	public void setdeveloper_id(String developer_id) {
		this.developer_id = developer_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getstudio_name() {
		return studio_name;
	}
	public void setstudio_name(String studio_name) {
		this.studio_name = studio_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getRevenue() {
		return revenue;
	}
	public void setRevenue(float revenue) {
		this.revenue = revenue;
	}
	
}
