package com.example.gamestore.entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Game")
public class Game {
	@Id
	private String game_id; 
	private String developer_id; 
	private String game_name; 
	private String description; 
	private float price; 
	private Date release_date; 
	private int rating; 
	private String thumbnail; 
	private String status; 
	public Game() { }
	public Game(String game_id, String developer_id, String game_name, String description, float price, Date release_date,
			int rating, String thumbnail, String status) {
		super();
		this.game_id = game_id;
		this.developer_id = developer_id;
		this.game_name = game_name;
		this.description = description;
		this.price = price;
		this.release_date = release_date;
		this.rating = rating;
		this.thumbnail = thumbnail;
		this.status = status;
	}
	public String getgame_id() {
		return game_id;
	}
	public void setgame_id(String game_id) {
		this.game_id = game_id;
	}
	public String getdeveloper_id() {
		return developer_id;
	}
	public void setdeveloper_id(String developer_id) {
		this.developer_id = developer_id;
	}
	public String getgame_name() {
		return game_name;
	}
	public void setgame_name(String game_name) {
		this.game_name = game_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getrelease_date() {
		return release_date;
	}
	public void setrelease_date(Date release_date) {
		this.release_date = release_date;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
