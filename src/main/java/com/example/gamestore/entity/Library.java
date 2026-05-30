package com.example.gamestore.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "Library")
public class Library {
	@Id
	private String library_id; 
	private String username; 
	private String game_id; 
	private Date purchase_date; 
	public Library() { }
	public Library(String library_id, String username, String game_id, Date purchase_date) {
		super();
		this.library_id = library_id;
		this.username = username;
		this.game_id = game_id;
		this.purchase_date = purchase_date;
	}
	public String getlibrary_id() {
		return library_id;
	}
	public void setlibrary_id(String library_id) {
		this.library_id = library_id;
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
	public Date getpurchase_date() {
		return purchase_date;
	}
	public void setpurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}
	
}
