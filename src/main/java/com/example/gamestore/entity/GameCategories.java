package com.example.gamestore.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "GameCategories")
public class GameCategories {
	@Id
	private String game_id; 
	private String category_id; 
	public GameCategories() { }
	public GameCategories(String game_id, String category_id) {
		super();
		this.game_id = game_id;
		this.category_id = category_id;
	}
	public String getgame_id() {
		return game_id;
	}
	public void setgame_id(String game_id) {
		this.game_id = game_id;
	}
	public String getcategory_id() {
		return category_id;
	}
	public void setcategory_id(String category_id) {
		this.category_id = category_id;
	}
	
}
