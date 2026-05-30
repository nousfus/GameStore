package com.example.gamestore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GameRequirements")
public class GameRequirements {
	@Id
	private String requirement_id; 
	private String game_id; 
	private String requirement_type; 
	private String os; 
	private String processor; 
	private String memory_ram; 
	private String graphicscard; 
	private String directx; 
	private String storage; 
	public GameRequirements() { }
	public GameRequirements(String requirement_id, String game_id, String requirement_type, String os, String processor,
			String memory_ram, String graphicscard, String directx, String storage) {
		super();
		this.requirement_id = requirement_id;
		this.game_id = game_id;
		this.requirement_type = requirement_type;
		this.os = os;
		this.processor = processor;
		this.memory_ram = memory_ram;
		this.graphicscard = graphicscard;
		this.directx = directx;
		this.storage = storage;
	}
	public String getrequirement_id() {
		return requirement_id;
	}
	public void setrequirement_id(String requirement_id) {
		this.requirement_id = requirement_id;
	}
	public String getgame_id() {
		return game_id;
	}
	public void setgame_id(String game_id) {
		this.game_id = game_id;
	}
	public String getrequirement_type() {
		return requirement_type;
	}
	public void setrequirement_type(String requirement_type) {
		this.requirement_type = requirement_type;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	public String getmemory_ram() {
		return memory_ram;
	}
	public void setmemory_ram(String memory_ram) {
		this.memory_ram = memory_ram;
	}
	public String getgraphicscard() {
		return graphicscard;
	}
	public void setgraphicscard(String graphicscard) {
		this.graphicscard = graphicscard;
	}
	public String getDirectx() {
		return directx;
	}
	public void setDirectx(String directx) {
		this.directx = directx;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	
}
