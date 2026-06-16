package com.example.gamestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamestore.dao.CategoriesDao;
import com.example.gamestore.dao.GameCategoriesDao;
import com.example.gamestore.dao.UserDao;
import com.example.gamestore.dao.UserRolesDao;
import com.example.gamestore.entity.GameCategories;

@Controller
public class demo_2 {
	@Autowired
	CategoriesDao cdao;
	@Autowired
	GameCategoriesDao gamecategorydao;
	@Autowired
	UserRolesDao userroledao;
	
	@RequestMapping("/abc")
	public String haha(Model m) {
		m.addAttribute("dsrole",userroledao.findByUsername("admin01"));
		return "demo_2";
	}

}
