package com.example.gamestore.entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Reviews")
public class Reviews {
	@Id
	private String review_id; 
	private String username; 
	private String game_id; 
	private int rating; 
	private String comment; 
	private Date created_at; 
	public Reviews() { }
	public Reviews(String review_id, String username, String game_id, int rating, String comment, Date created_at) {
		super();
		this.review_id = review_id;
		this.username = username;
		this.game_id = game_id;
		this.rating = rating;
		this.comment = comment;
		this.created_at = created_at;
	}
	public String getreview_id() {
		return review_id;
	}
	public void setreview_id(String review_id) {
		this.review_id = review_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getgame_id() {
		return game_id;
	}
	public void setgame_id(String game_id) {
		this.game_id = game_id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getcreated_at() {
		return created_at;
	}
	public void setcreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
}
