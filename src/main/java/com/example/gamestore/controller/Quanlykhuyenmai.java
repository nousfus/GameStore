package com.example.gamestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class Quanlykhuyenmai {
@RequestMapping("/admin/khuyenmai")
	public String khuyenmai(){
	return "Admin/promotions";
	}
}
