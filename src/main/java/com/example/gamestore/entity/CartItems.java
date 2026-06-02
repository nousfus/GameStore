package com.example.gamestore.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CartItems")
public class CartItems {
	@Id
	private String cart_item_id; 
	@Column(name = "cart_id")
	private String cartId;
	@Column(name = "game_id")
	private String gameId; 
	private int quantity; 
	public CartItems() { }
	public CartItems(String cart_item_id, String cart_id, String game_id, int quantity) {
		super();
		this.cart_item_id = cart_item_id;
		this.cartId = cart_id;
		this.gameId = game_id;
		this.quantity = quantity;
	}
	public String getCart_item_id() {
		return cart_item_id;
	}
	public void setCart_item_id(String cart_item_id) {
		this.cart_item_id = cart_item_id;
	}
	public String getCart_id() {
		return cartId;
	}
	public void setCart_id(String cart_id) {
		this.cartId = cart_id;
	}
	public String getGame_id() {
		return gameId;
	}
	public void setGame_id(String game_id) {
		this.gameId = game_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
