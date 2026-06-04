package com.example.gamestore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GameImages")
public class GameImages {
	@Id
	private String image_id; 
	private String game_id; 
	private String image_url; 
	private int display_order; 
	public GameImages() { }
	public GameImages(String image_id, String game_id, String image_url, int display_order) {
		super();
		this.image_id = image_id;
		this.game_id = game_id;
		this.image_url = image_url;
		this.display_order = display_order;
	}
	public String getImage_id() {
		return image_id;
	}
	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}
	public String getGame_id() {
		return game_id;
	}
	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public int getDisplay_order() {
		return display_order;
	}
	public void setDisplay_order(int display_order) {
		this.display_order = display_order;
	}
	
}
