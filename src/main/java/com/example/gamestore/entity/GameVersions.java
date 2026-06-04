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
	public String getVersion_id() {
		return version_id;
	}
	public void setVersion_id(String version_id) {
		this.version_id = version_id;
	}
	public String getGame_id() {
		return game_id;
	}
	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}
	public String getVersion_number() {
		return version_number;
	}
	public void setVersion_number(String version_number) {
		this.version_number = version_number;
	}
	public String getUpdate_description() {
		return update_description;
	}
	public void setUpdate_description(String update_description) {
		this.update_description = update_description;
	}
	public Date getRelease_date() {
		return release_date;
	}
	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}
	
}
