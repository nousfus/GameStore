package com.example.gamestore.entity;
import jakarta.persistence.Entity;

import com.example.gamestore.entity.GameCategoriesId;

import jakarta.persistence.*;

@Entity
@IdClass(GameCategoriesId.class)
@Table(name = "GameCategories")
public class GameCategories {

    @Id
    private String game_id;

    @Id
    private String category_id;

    public GameCategories() {}

    public GameCategories(String game_id, String category_id) {
        this.game_id = game_id;
        this.category_id = category_id;
    }
	public String getGame_id() {
		return game_id;
	}
	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	
}
