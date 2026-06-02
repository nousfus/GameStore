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
	public String getOrder_detail_id() {
		return order_detail_id;
	}
	public void setOrder_detail_id(String order_detail_id) {
		this.order_detail_id = order_detail_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getGame_id() {
		return game_id;
	}
	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscount_amount() {
		return discount_amount;
	}
	public void setDiscount_amount(float discount_amount) {
		this.discount_amount = discount_amount;
	}
	
}
