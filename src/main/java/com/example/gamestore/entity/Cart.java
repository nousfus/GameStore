package com.example.gamestore.entity;

import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Cart")
public class Cart {
	@Id
	private String cart_id; 
	private String username; 
	private Date created_at;
	private String status; 
	public Cart() { }
	public Cart(String cart_id, String username, Date created_at, String status) {
		super();
		this.cart_id = cart_id;
		this.username = username;
		this.created_at = created_at;
		this.status = status;
	}
	public String getcart_id() {
		return cart_id;
	}
	public void setcart_id(String cart_id) {
		this.cart_id = cart_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getcreated_at() {
		return created_at;
	}
	public void setcreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
