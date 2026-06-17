package com.example.gamestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductList {
	@RequestMapping("/user/product-detail")
	public String details() {
		return "User/product-detail";
	}
}
