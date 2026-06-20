package com.example.gamestore.entity;

import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Game")
public class Game {
	@Id
	private String game_id; 
	@ManyToOne
    @JoinColumn(name = "developer_id")
    private DeveloperProfiles developer;
	private String game_name; 
	private String description; 
	private float price; 
	private Date release_date; 
	private int rating; 
	private String thumbnail; 
	private String status; 
	private String video_url;
	private String ram;
	private String storage;
	@Transient
	private String categories;

	public String getCategories() {
	    return categories;
	}

	public void setCategories(String categories) {
	    this.categories = categories;
	}
	public Game() { }
	public Game(String game_id, DeveloperProfiles developer_id, String game_name, String description, float price, Date release_date,
			int rating, String thumbnail, String status, String video_url, String ram, String storage) {
		super();
		this.game_id = game_id;
		this.developer = developer_id;
		this.game_name = game_name;
		this.description = description;
		this.price = price;
		this.release_date = release_date;
		this.rating = rating;
		this.thumbnail = thumbnail;
		this.status = status;
		this.video_url = video_url;
		this.ram = ram;
		this.storage = storage;
	}
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public String getGame_id() {
		return game_id;
	}
	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}
	public DeveloperProfiles getDeveloper() {
		return developer;
	}
	public void setDeveloper(DeveloperProfiles developer) {
		this.developer = developer;
	}
	public String getGame_name() {
		return game_name;
	}
	public void setGame_name(String game_name) {
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
	public Date getRelease_date() {
		return release_date;
	}
	public void setRelease_date(Date release_date) {
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
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	
}
