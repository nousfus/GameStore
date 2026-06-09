package com.example.gamestore.controller;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamestore.dao.RolesDao;
import com.example.gamestore.dao.UserDao;
import com.example.gamestore.dao.UserRolesDao;
import com.example.gamestore.entity.Roles;
import com.example.gamestore.entity.UserRoles;
import com.example.gamestore.entity.Users;

import jakarta.servlet.http.HttpSession;

@Controller
public class DangNhap_DangKy {
	@Autowired
	UserDao udao;
	@Autowired
	UserRolesDao userroledao;
	@Autowired
	RolesDao roledao;
	@Autowired
	HttpSession session;
	@RequestMapping("/login-register")
	public String dndk(Model m) {
		return "User/login-register";
	}
	@PostMapping("/user/login")
	public String check(Model m, 
			@RequestParam("username") String username,
			@RequestParam("password")String password) {
		Users user = username.contains("@") ? udao.findByEmail(username) : udao.findById(username).orElse(null);
		if(user==null) {
			m.addAttribute("kq","Tài khoản không tồn tại");
		}else {
			String kq = password.equals(user.getPassword()) ? "Đăng nhập thành công" : "Mật khẩu không chính xác";
			m.addAttribute("kq",kq);
			System.out.println(kq);
			session.setAttribute("user", user);
		}
		return "forward:/login-register";
	}
	@PostMapping("/dk")
	public String check2(Model m, 
			@RequestParam("fullName") String fullname,
			@RequestParam("email")String email,
			@RequestParam("username") String username,
			@RequestParam("password")String password,
			@RequestParam("confirmPassword")String password2,
			@RequestParam("acceptTerms")boolean acceptTerms) {
		if(!password.equals(password2)) {
			System.out.println("Đăng nhập thất bại");
			System.out.println("Mật khẩu không trùng");
			m.addAttribute("kq","Mật khẩu không trùng");
		}
		if(!acceptTerms) {
			System.out.println("Đăng nhập thất bại");
			System.out.print("Vui lòng đồng ý với Điều khoản dịch vụ");
		}else {
			Date date = new Date(System.currentTimeMillis());
			Users user = new Users(username,email,password,fullname,"a",date,"Active");
			Roles role = roledao.findByRoleName("Customer");
			userroledao.save(new UserRoles(user.getUsername(),role.getrole_id()));
			udao.save(user);
		}
		return "forward:/login-register";
	}
}
