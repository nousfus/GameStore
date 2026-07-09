package com.example.gamestore.entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "WishList")
public class WishList {
	@Id
	private String wishlist_id; 
	private String username; 
   @ManyToOne
    @JoinColumn(name = "game_id")
	private Game game; 
	private Date added_at; 
	public WishList() { }
	public WishList(String wishlist_id, String username, Game game, Date added_at) {
		super();
		this.wishlist_id = wishlist_id;
		this.username = username;
		this.game = game;
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
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Date getAdded_at() {
		return added_at;
	}
	public void setAdded_at(Date added_at) {
		this.added_at = added_at;
	}
	
}

