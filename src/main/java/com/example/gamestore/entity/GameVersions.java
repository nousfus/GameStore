package com.example.gamestore.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "GameVersions")
public class GameVersions {
	@Id
	private String version_id; 
	private String game_id; 
	private String version_number; 
	private String update_description; 
	private Date release_date; 
	public GameVersions() { }
	public GameVersions(String version_id, String game_id, String version_number, String update_description,
			Date release_date) {
		super();
		this.version_id = version_id;
		this.game_id = game_id;
		this.version_number = version_number;
		this.update_description = update_description;
		this.release_date = release_date;
	}
	public String getversion_id() {
		return version_id;
	}
	public void setversion_id(String version_id) {
		this.version_id = version_id;
	}
	public String getgame_id() {
		return game_id;
	}
	public void setgame_id(String game_id) {
		this.game_id = game_id;
	}
	public String getversion_number() {
		return version_number;
	}
	public void setversion_number(String version_number) {
		this.version_number = version_number;
	}
	public String getupdate_description() {
		return update_description;
	}
	public void setupdate_description(String update_description) {
		this.update_description = update_description;
	}
	public Date getrelease_date() {
		return release_date;
	}
	public void setrelease_date(Date release_date) {
		this.release_date = release_date;
	}
	
}
