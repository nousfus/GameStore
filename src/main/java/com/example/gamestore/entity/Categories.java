package com.example.gamestore.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Categories")
public class Categories {
	@Id
	private String category_id ; 
	private String category_name;
	private String description; 
	public Categories() { }
	public Categories(String category_id, String category_name, String description) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.description = description;
	}
	public String getcategory_id() {
		return category_id;
	}
	public void setcategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getcategory_name() {
		return category_name;
	}
	public void setcategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
