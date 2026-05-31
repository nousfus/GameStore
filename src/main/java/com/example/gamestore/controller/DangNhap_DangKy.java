package com.example.gamestore.controller;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamestore.dao.UserDao;
import com.example.gamestore.entity.Users;

@Controller
public class DangNhap_DangKy {
	@Autowired
	UserDao udao;
	@RequestMapping("/dndk")
	public String dndk(Model m) {
		return "dndk";
	}
	@PostMapping("/check")
	public String check(Model m, 
			@RequestParam("username_dn") String username,
			@RequestParam("password_dn")String password) {
		Users user = username.contains("@") ? udao.findByEmail(username) : udao.findById(username).orElse(null);
		if(user==null) {
			m.addAttribute("kq","Tài khoản không tồn tại");
		}else {
			String kq = password.equals(user.getPassword()) ? "Đăng nhập thành công" : "Mật khẩu không chính xác";
			m.addAttribute("kq",kq);
		}
		return "forward:/dndk";
	}
	@PostMapping("/dk")
	public String check2(Model m, 
			@RequestParam("fullname") String fullname,
			@RequestParam("email")String email,
			@RequestParam("username") String username,
			@RequestParam("password")String password,
			@RequestParam("password2")String password2) {
		if(!password.equals(password2)) {
			m.addAttribute("kq","Mật khẩu không trùng");
		}
		Date date = new Date();
		Users user = new Users(username,email,password,fullname,"a",date,"Active");
		udao.save(user);
		return "forward:/dndk";
	}
}
