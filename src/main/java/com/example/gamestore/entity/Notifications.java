package com.example.gamestore.entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Notifications")
public class Notifications {
	@Id
	private String notification_id; 
	private String username; 
	private String title; 
	private String content; 
	private boolean is_read; 
	private Date created_at; 
	public Notifications() { }
	public Notifications(String notification_id, String username, String title, String content, boolean is_read,
			Date created_at) {
		super();
		this.notification_id = notification_id;
		this.username = username;
		this.title = title;
		this.content = content;
		this.is_read = is_read;
		this.created_at = created_at;
	}
	public String getnotification_id() {
		return notification_id;
	}
	public void setnotification_id(String notification_id) {
		this.notification_id = notification_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean is_read() {
		return is_read;
	}
	public void setRead(boolean is_read) {
		this.is_read = is_read;
	}
	public Date getcreated_at() {
		return created_at;
	}
	public void setcreated_at(Date created_at) {
		this.created_at = created_at;
	}
	
}
