package com.example.gamestore.controller;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamestore.dao.RolesDao;
import com.example.gamestore.dao.UserDao;
import com.example.gamestore.dao.UserRolesDao;
import com.example.gamestore.entity.Roles;
import com.example.gamestore.entity.UserRoles;
import com.example.gamestore.entity.Users;
import com.example.gamestore.service.EmailService;

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
    private EmailService emailService;
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
			return "forward:/login-register";
		}else if(password.equals(user.getPassword())){
			session.setAttribute("user", user);
			return "forward:/";
		}else {
			System.out.println("Mật khẩu không chính xác");
			return "forward:/login-register";
		}
	}
	@PostMapping("/user/register")
	public String check2(Model m, 
			@RequestParam("fullName") String fullname,
			@RequestParam("email")String email,
			@RequestParam("username") String username,
			@RequestParam("password")String password,
			@RequestParam("confirmPassword")String password2) {
		if(!password.equals(password2)) {							//Mật khẩu không trùng
			m.addAttribute("kq","Mật khẩu không trùng");
		}else {
			Date date = new Date(System.currentTimeMillis());
			Users user = new Users(username,email,password,fullname,"a",date,"Active");
			Roles role = roledao.findByRoleName("Customer");
			userroledao.save(new UserRoles(user.getUsername(),role.getrole_id()));
			udao.save(user);
		}
		return "forward:/CheckRegister";
	}
	@GetMapping("/CheckRegister")
    public String sendMail() {
		Users user = (Users) session.getAttribute("user");
        emailService.sendEmail(
            user.getEmail(),
            "Thông báo đổi mật khẩu",
            "Mã OTP đổi mật khẩu của bạn là "+(String) session.getAttribute("otp")
        );
        return "redirect:/verify";
    }

}
