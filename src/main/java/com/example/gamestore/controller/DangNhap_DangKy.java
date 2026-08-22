package com.example.gamestore.controller;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamestore.dao.CartDao;
import com.example.gamestore.dao.DeveloperProfilesDao;
import com.example.gamestore.dao.RolesDao;
import com.example.gamestore.dao.UserDao;
import com.example.gamestore.dao.UserRolesDao;
import com.example.gamestore.entity.Cart;
import com.example.gamestore.entity.DeveloperProfiles;
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
	@Autowired
	CartDao cartdao;
	@Autowired
	DeveloperProfilesDao devdao;
	@Autowired
    private EmailService emailService;
    public String generateOTP() {				// Tạo mã OTP xác nhận email
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
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
			@RequestParam("confirmPassword")String password2,
			@RequestParam("devStudio") String devStudio,
			@RequestParam("devBio") String devBio,
			@RequestParam(value = "isDeveloper", required = false, defaultValue = "false") boolean isDeveloper) {
		if(!password.equals(password2)) {							//Mật khẩu không trùng
			m.addAttribute("kq","Mật khẩu không trùng");
			System.out.println("Mật khẩu không trùng");
		}else {
			Date date = new Date(System.currentTimeMillis());
			Users user = new Users(username,email,password,fullname,"a",date,"Active");
			session.setAttribute("user", user);
			session.setAttribute("otp", generateOTP());
			if(isDeveloper) { 
	            List<DeveloperProfiles> list = devdao.findAll(); 
	            String devId = "DEV001";
	            if (!list.isEmpty()) {
	                DeveloperProfiles dev = list.get(list.size()-1); 
	                devId = "DEV00" + (Integer.parseInt(dev.getDeveloperid().substring(5)) + 1); 
	            }
	            devdao.save(new DeveloperProfiles(devId, user.getUsername(), devStudio, devBio, 0)); 
	        }
		}
		return "redirect:/CheckRegister";
	}
	@GetMapping("/CheckRegister")
    public String sendMail() {
		Users user = (Users) session.getAttribute("user");
        emailService.sendEmail(
            user.getEmail(),
            "Thông báo đăng ký tài khoản",
            "Mã OTP đăng ký của bạn là "+(String) session.getAttribute("otp")
        );
        return "redirect:/verify-register";
    }
	@RequestMapping("/verify-register")
	public String verify(Model m) {
		return "User/verify-email_register";
	}
	@PostMapping("/verify-get-register")	
	public String verify_get(Model m,@RequestParam("code")String code) {
		if(code.equals((String)session.getAttribute("otp"))){
			Users user = (Users)session.getAttribute("user");
			Roles role = roledao.findByRoleName("Customer");
			udao.save(user);
			userroledao.save(new UserRoles(user.getUsername(),role.getRoleId()));
			session.setAttribute("user", user);
			session.removeAttribute("otp");
			
			// Tạo giỏ hàng cho người dùng mới
			List<Cart> cartList = cartdao.findAll();
			Cart cart = cartList.get(cartdao.findAll().size()-1);
			int id = Integer.parseInt(cart.getCart_id().substring(6));
			String newCartId = "CART00"+(id+1);
			
			Date date = new Date(System.currentTimeMillis());
			cartdao.save(new Cart(newCartId,user.getUsername(),date,"Open"));
			return "redirect:/";
		}else {
			System.out.println("Mã xác nhận không đúng");
			return "redirect:/verify-register";
		}
	}
	@RequestMapping("/resend-register")
	public String resend() {
		session.setAttribute("otp",generateOTP());
		return "redirect:/CheckRegister";
	}

}
