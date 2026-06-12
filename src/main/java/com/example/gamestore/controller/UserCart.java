package com.example.gamestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserCart {
	@RequestMapping("/user/cart")
	public String cart() {
		return "User/cart";
	}
}
