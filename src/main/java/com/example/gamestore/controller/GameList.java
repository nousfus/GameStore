package com.example.gamestore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamestore.dao.CategoriesDao;
import com.example.gamestore.dao.GameDao;
import com.example.gamestore.entity.Game;

import jakarta.servlet.http.HttpSession;

@Controller
public class GameList {
	@Autowired
	HttpSession session;
	@Autowired
	CategoriesDao categorydao;
	@Autowired
	GameDao gamedao;
	@GetMapping("/user/product-list")
	public String productList(
	        @RequestParam(defaultValue = "0") int page,
	        Model m) {
		m.addAttribute("dscate",categorydao.findAll());
		Pageable pageable = PageRequest.of(page, 9);
		Page<Game> gamePage = gamedao.findAllGame(pageable);

	    m.addAttribute("gamePage", gamePage);
	    m.addAttribute("currentPage", page);

	    return "user/product-list";
	}
	@RequestMapping("/user/product-detail/{id}")
	public String detail(@PathVariable("id") String id,Model m) {
		m.addAttribute("game",gamedao.findById(id).orElse(null));
		return "User/product-detail";
	}
}
