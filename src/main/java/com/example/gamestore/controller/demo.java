package com.example.gamestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class demo {
	@RequestMapping("/")
	public String abc(Model model) {
		model.addAttribute("a","Hello World");
		return "demo";
	}
}
