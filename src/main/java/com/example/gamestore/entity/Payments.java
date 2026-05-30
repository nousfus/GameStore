package com.example.gamestore.entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name ="Payments")
public class Payments {
	@Id
	private String payment_id; 
	private String order_id; 
	private String payment_method;
	private String payment_status; 
	private Date paid_at; 
	private String transactionCode;
	public Payments() {}
	public Payments(String payment_id, String order_id, String payment_method, String payment_status, Date paid_at,
			String transactionCode) {
		super();
		this.payment_id = payment_id;
		this.order_id = order_id;
		this.payment_method = payment_method;
		this.payment_status = payment_status;
		this.paid_at = paid_at;
		this.transactionCode = transactionCode;
	}
	public String getpayment_id() {
		return payment_id;
	}
	public void setpayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	public String getorder_id() {
		return order_id;
	}
	public void setorder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getpayment_method() {
		return payment_method;
	}
	public void setpayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public String getpayment_status() {
		return payment_status;
	}
	public void setpayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public Date getpaid_at() {
		return paid_at;
	}
	public void setpaid_at(Date paid_at) {
		this.paid_at = paid_at;
	}
	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	
}
