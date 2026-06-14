package com.example.gamestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gamestore.dao.CartDao;
import com.example.gamestore.dao.CartItemsDao;
import com.example.gamestore.dao.UserDao;
import com.example.gamestore.entity.Cart;
import com.example.gamestore.entity.CartItems;
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
	@RequestMapping("/")
	public String abc(Model model) {
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			model.addAttribute("username",user.getUsername());
		}
		return "trangchu";
	}
	@RequestMapping("/user/logout")										//Đăng xuất
	public String logout() {
		session.removeAttribute("user");
		return "redirect:/";
	}	
	@RequestMapping("/user/profile")									// Profile
	public String profile(Model m) {
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			m.addAttribute("user",user);
		}
		return "User/profile";
	}
	@RequestMapping("/carttemp")
	public String a(Model m) {
		Users user = (Users) session.getAttribute("user");
		List<Cart> list = cartdao.findByUsername(user.getUsername());
		List<CartItems> list0 = cartitemdao.findByCartId(list.get(0).getCart_id());
		session.setAttribute("list0",list0);
		return "forward:/user/cart";
	}
}
