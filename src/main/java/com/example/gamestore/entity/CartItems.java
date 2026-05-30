package com.example.gamestore.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CartItems")
public class CartItems {
	@Id
	private String cart_item_id; 
	private String cart_id; 
	private String game_id; 
	private int quantity; 
	public CartItems() { }
	public CartItems(String cart_item_id, String cart_id, String game_id, int quantity) {
		super();
		this.cart_item_id = cart_item_id;
		this.cart_id = cart_id;
		this.game_id = game_id;
		this.quantity = quantity;
	}
	public String getcart_item_id() {
		return cart_item_id;
	}
	public void setcart_item_id(String cart_item_id) {
		this.cart_item_id = cart_item_id;
	}
	public String getcart_id() {
		return cart_id;
	}
	public void setcart_id(String cart_id) {
		this.cart_id = cart_id;
	}
	public String getgame_id() {
		return game_id;
	}
	public void setgame_id(String game_id) {
		this.game_id = game_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
