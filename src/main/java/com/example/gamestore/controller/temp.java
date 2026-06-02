package com.example.gamestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class temp {
	@RequestMapping("/a")
	public String hello(Model m,  @RequestParam(value = "quantity", defaultValue = "1") int quantity) {
		m.addAttribute("quantity",quantity);
		return "abc";
	}
	@PostMapping("/checkout2")
	public String checkout(@RequestParam("pay") String pay, Model m) {
		m.addAttribute("m",pay);
		return "abc";
	}
	@RequestMapping("/sum")
	public String sum() {
		return "abc";
	}
}
