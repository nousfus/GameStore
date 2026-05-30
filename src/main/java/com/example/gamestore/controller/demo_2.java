package com.example.gamestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gamestore.dao.UserDao;

@Controller
public class demo_2 {
	@Autowired
	UserDao udao;
	
	@RequestMapping("/abc")
	public String haha(Model m) {
		m.addAttribute("a",udao.findAll());
		return "demo_2";
	}
}
