package com.example.gamestore.entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name ="Payments")
public class Payments {
	@Id
	private String payment_id; 
	@Column(name = "order_id")
	private String orderid; 
	private String payment_method;
	private String payment_status; 
	private Date paid_at; 
	private String transaction_code;
	public Payments() {}
	public Payments(String payment_id, String order_id, String payment_method, String payment_status, Date paid_at,
			String transactionCode) {
		super();
		this.payment_id = payment_id;
		this.orderid = order_id;
		this.payment_method = payment_method;
		this.payment_status = payment_status;
		this.paid_at = paid_at;
		this.transaction_code = transactionCode;
	}
	public String getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String order_id) {
		this.orderid = order_id;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public Date getPaid_at() {
		return paid_at;
	}
	public void setPaid_at(Date paid_at) {
		this.paid_at = paid_at;
	}
	public String getTransactionCode() {
		return transaction_code;
	}
	public void setTransactionCode(String transactionCode) {
		this.transaction_code = transactionCode;
	}
	
	
}
