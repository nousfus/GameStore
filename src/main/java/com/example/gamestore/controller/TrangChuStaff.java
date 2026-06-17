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
@RequestMapping("/staff")
public class TrangChuStaff {
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
		return "staff/home";
	}
	@RequestMapping("/profile")									// Profile
	public String profile(Model m) {
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			m.addAttribute("username",user.getUsername());
			m.addAttribute("user",user);
			List<UserRoles> list = userroledao.findByUsername(user.getUsername());
			List<Roles> roles = new ArrayList<>();
			for(UserRoles ur : list) {
			    Roles role = roledao.findById(ur.getRole_id()).orElse(null);
			    if(role != null) {
			        roles.add(role);
			    }
			}
			m.addAttribute("dsrole",roles);
		}
		return "User/profile";
	}
	@RequestMapping("/notifications")
	public String notification() {
		return "staff/notifications";
	}
	@RequestMapping("/orders")
	public String orders() {
		return "staff/orders-management";
	}
	@RequestMapping("/reviews")
	public String reviews() {
		return "staff/reviews-management";
	}
}
