package com.example.gamestore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GameImages")
public class GameImages {
	@Id
	private String image_id; 
	@Column(name = "game_id")
	private String gameId; 
	private String image_url; 
	public GameImages() { }
	public GameImages(String image_id, String game_id, String image_url) {
		super();
		this.image_id = image_id;
		this.gameId = game_id;
		this.image_url = image_url;
	}
	public String getImage_id() {
		return image_id;
	}
	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}
	public String getGame_id() {
		return gameId;
	}
	public void setGame_id(String game_id) {
		this.gameId = game_id;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
}
