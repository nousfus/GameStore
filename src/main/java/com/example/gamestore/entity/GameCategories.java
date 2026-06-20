package com.example.gamestore.entity;
import jakarta.persistence.Entity;

import com.example.gamestore.service.GameCategoriesId;

import jakarta.persistence.*;

@Entity
@IdClass(GameCategoriesId.class)
@Table(name = "GameCategories")
public class GameCategories {

    @Id
    @Column(name = "game_id")
    private String gameid;

    @Id
    private String category_id;

    public GameCategories() {}

    public GameCategories(String game_id, String category_id) {
        this.gameid = game_id;
        this.category_id = category_id;
    }
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String game_id) {
		this.gameid = game_id;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	
}
