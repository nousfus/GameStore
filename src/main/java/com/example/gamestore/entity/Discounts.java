package com.example.gamestore.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "Discounts")
public class Discounts {
	@Id
	private String discount_id; 
	@Column(name = "game_id")
	private String gameId;
	private float discount_percent; 
	private Date start_date; 	
	private Date end_date; 
	private String status; 
	public Discounts() { }
	public Discounts(String discount_id, String game_id, float discount_percent, Date start_date, Date end_date,
			String status) {
		super();
		this.discount_id = discount_id;
		this.gameId = game_id;
		this.discount_percent = discount_percent;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
	}
	public String getdiscount_id() {
		return discount_id;
	}
	public void setdiscount_id(String discount_id) {
		this.discount_id = discount_id;
	}
	public String getgame_id() {
		return gameId;
	}
	public void setgame_id(String game_id) {
		this.gameId = game_id;
	}
	public float getdiscount_percent() {
		return discount_percent;
	}
	public void setdiscount_percent(float discount_percent) {
		this.discount_percent = discount_percent;
	}
	public Date getstart_date() {
		return start_date;
	}
	public void setstart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getend_date() {
		return end_date;
	}
	public void setend_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
