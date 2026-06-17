package com.example.gamestore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gamestore.dao.RolesDao;
import com.example.gamestore.dao.UserRolesDao;
import com.example.gamestore.entity.Roles;
import com.example.gamestore.entity.UserRoles;
import com.example.gamestore.entity.Users;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class TrangChuAdmin {
	@Autowired
	HttpSession session;
	@Autowired
	UserRolesDao userroledao;
	@Autowired
	RolesDao roledao;
	@RequestMapping("/home")
	public String main(Model m) {
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			m.addAttribute("username",user.getUsername());
		}
		return "Admin/home";
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
	@RequestMapping("/content-management")
	public String content() {
		return "Admin/content-management";
	}
	@RequestMapping("/user-management")
	public String user() {
		return "Admin/user-management";
	}
	@RequestMapping("/promotions")
	public String promotions() {
		return "Admin/promotions";
	}
	@RequestMapping("/revenue-report")
	public String revenuereport() {
		return "Admin/revenue-report";
	}
}
