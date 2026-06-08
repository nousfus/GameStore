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
import com.example.gamestore.entity.GameCategories;

@Controller
public class demo_2 {
	@Autowired
	CategoriesDao cdao;
	@Autowired
	GameCategoriesDao gamecategorydao;
	
	@GetMapping("/abc")
	public String haha(Model m) {
		m.addAttribute("dscategory",cdao.findAll());
		return "demo_2";
	}
	@PostMapping("/abc2")
	public String abc(
	        @RequestParam("category") String[] categories,
	        Model m) {

	    for(String category : categories){
	        gamecategorydao.save(
	            new GameCategories("GM001", category)
	        );
	    }

	    return "demo_2";
	}
}
