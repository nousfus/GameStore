package com.example.gamestore.entity;

import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Orders")
public class Orders {
	@Id
    private String order_id;
	private String username; 
	private Date order_date; 
	private int total_amount; 
	private String status;
	public Orders() {}
	public Orders(String order_id, String username, Date order_date, int total_amount, String status) {
		super();
		this.order_id = order_id;
		this.username = username;
		this.order_date = order_date;
		this.total_amount = total_amount;
		this.status = status;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
