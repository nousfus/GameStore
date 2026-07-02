package com.example.gamestore.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CartItems")
public class CartItems {
	@Id
	private String cart_item_id; 
	@Column(name = "cart_id")
	private String cartId;
   @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
	public CartItems() { }
	public CartItems(String cart_item_id, String cart_id, Game game) {
		super();
		this.cart_item_id = cart_item_id;
		this.cartId = cart_id;
		this.game = game;
	}
	public String getCart_item_id() {
		return cart_item_id;
	}
	public void setCart_item_id(String cart_item_id) {
		this.cart_item_id = cart_item_id;
	}
	
	public String getCartid() {
		return cartId;
	}
	public void setCartid(String cart_id) {
		this.cartId = cart_id;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
}
