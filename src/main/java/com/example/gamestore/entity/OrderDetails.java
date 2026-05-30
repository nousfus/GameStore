package com.example.gamestore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "OrderDetails")
public class OrderDetails {
	@Id
	private String order_detail_id; 
	private String order_id; 
	private String game_id; 
	private float price; 
	private float discount_amount; 
	public OrderDetails() { }
	public OrderDetails(String order_detail_id, String order_id, String game_id, float price, float discount_amount) {
		super();
		this.order_detail_id = order_detail_id;
		this.order_id = order_id;
		this.game_id = game_id;
		this.price = price;
		this.discount_amount = discount_amount;
	}
	public String getorder_detail_id() {
		return order_detail_id;
	}
	public void setorder_detail_id(String order_detail_id) {
		this.order_detail_id = order_detail_id;
	}
	public String getorder_id() {
		return order_id;
	}
	public void setorder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getgame_id() {
		return game_id;
	}
	public void setgame_id(String game_id) {
		this.game_id = game_id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getdiscount_amount() {
		return discount_amount;
	}
	public void setdiscount_amount(float discount_amount) {
		this.discount_amount = discount_amount;
	}
	
}
