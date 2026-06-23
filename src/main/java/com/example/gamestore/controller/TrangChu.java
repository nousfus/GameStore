package com.example.gamestore.controller;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamestore.dao.CartDao;
import com.example.gamestore.dao.CartItemsDao;
import com.example.gamestore.dao.CategoriesDao;
import com.example.gamestore.dao.GameDao;
import com.example.gamestore.dao.RolesDao;
import com.example.gamestore.dao.UserDao;
import com.example.gamestore.dao.UserRolesDao;
import com.example.gamestore.entity.Cart;
import com.example.gamestore.entity.CartItems;
import com.example.gamestore.entity.Categories;
import com.example.gamestore.entity.Game;
import com.example.gamestore.entity.Roles;
import com.example.gamestore.entity.Users;

import jakarta.servlet.http.HttpSession;

@Controller
public class TrangChu {
	@Autowired
	HttpSession session;
	@Autowired
	UserDao userdao;
	@Autowired
	CartDao cartdao;
	@Autowired
	CartItemsDao cartitemdao;
	@Autowired
	UserRolesDao userroledao;
	@Autowired
	RolesDao roledao;
	@Autowired
	CategoriesDao categorydao;
	@Autowired
	GameDao gamedao;
	@RequestMapping("/")
	public String abc(Model m) {
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			m.addAttribute("username",user.getUsername());
			session.setAttribute("rolepicked", "User");
		}
		// Hiển thị sản phẩm
		List<Game> games = gamedao.findTop3ByRating(5);
		m.addAttribute("top3game",games);
		
		return "trangchu";
	}
	@RequestMapping("/user/logout")										//Đăng xuất
	public String logout() {
		session.removeAttribute("user");
		session.removeAttribute("rolepicked");
		return "redirect:/";
	}	
	@RequestMapping("/user/profile")									// Profile
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
	@PostMapping("/user/role")
	public String role(Model m, @RequestParam("role") String role) {
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			m.addAttribute("username",user.getUsername());
			m.addAttribute("user",user);
			List<Roles> list = userroledao.findByUsername(user.getUsername());
			m.addAttribute("dsrole",list);
		}
		if(role.equals("R01")) {
			session.setAttribute("rolepicked", "Admin");
			return "forward:/admin/home";
		}else if(role.equals("R04")){
			session.setAttribute("rolepicked", "Staff");
			return "forward:/staff/home";
		}else if(role.equals("R03")){
			session.setAttribute("rolepicked", "Developer");	
			return "forward:/developer/home";
		}
		else {
			return "User/profile";
		}
	}
	@RequestMapping("/user/product")
	public String product() {
		return "User/product-list";
	}
}