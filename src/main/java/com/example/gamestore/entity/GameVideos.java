package com.example.gamestore.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "GameVideos")
public class GameVideos {
	@Id
	private String video_id; 
	private String game_id; 
	private String video_url; 
	private String title; 
	public GameVideos() { }
	public GameVideos(String video_id, String game_id, String video_url, String title) {
		super();
		this.video_id = video_id;
		this.game_id = game_id;
		this.video_url = video_url;
		this.title = title;
	}
	public String getVideo_id() {
		return video_id;
	}
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}
	public String getGame_id() {
		return game_id;
	}
	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
