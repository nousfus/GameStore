package com.example.gamestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gamestore.dao.RolesDao;
import com.example.gamestore.dao.UserRolesDao;
import com.example.gamestore.entity.Roles;
import com.example.gamestore.entity.UserRoles;
import com.example.gamestore.entity.Users;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/developer")
public class TrangChuDeveloper {
	@Autowired
	HttpSession session;
	@Autowired
	UserRolesDao userroledao;
	@Autowired
	RolesDao roledao;
	@RequestMapping("/home")
	public String home(Model m) {
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			m.addAttribute("username",user.getUsername());
		}
		return "Developer/home";
	}
	@RequestMapping("/profile")									// Profile
	public String profile(Model m) {
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			m.addAttribute("rolepicked",session.getAttribute("rolepicked"));
			m.addAttribute("username",user.getUsername());
			m.addAttribute("user",user);
			List<Roles> list = userroledao.findByUsername(user.getUsername());
			m.addAttribute("dsrole",list);
		}
		return "User/profile";
	}
	@RequestMapping("/game-management")
	public String game_mana(Model m) {
		
		return "Developer/game-management";
	}
	@RequestMapping("/revenue-tracking")
	public String revenue(Model m) {
		
		return "Developer/revenue-tracking";
	}
	@RequestMapping("/review-feedback")
	public String reviews(Model m) {
		
		return "Developer/review-feedback";
	}
}
