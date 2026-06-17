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
	@RequestMapping("/admin/profile")									// Profile
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
