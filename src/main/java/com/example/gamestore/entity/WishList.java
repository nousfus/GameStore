package com.example.gamestore.entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "WishList")
public class WishList {
	@Id
	private String wishlist_id; 
	private String username; 
	private String game_id; 
	private Date added_at; 
	public WishList() { }
	public WishList(String wishlist_id, String username, String game_id, Date added_at) {
		super();
		this.wishlist_id = wishlist_id;
		this.username = username;
		this.game_id = game_id;
		this.added_at = added_at;
	}
	public String getwishlist_id() {
		return wishlist_id;
	}
	public void setwishlist_id(String wishlist_id) {
		this.wishlist_id = wishlist_id;
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
	public Date getadded_at() {
		return added_at;
	}
	public void setadded_at(Date added_at) {
		this.added_at = added_at;
	}
	
}

