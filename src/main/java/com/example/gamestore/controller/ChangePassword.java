package com.example.gamestore.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gamestore.dao.UserDao;
import com.example.gamestore.entity.Users;
import com.example.gamestore.service.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChangePassword {
	@Autowired
	UserDao userdao;
	@Autowired
	HttpSession session;
    @Autowired
    private EmailService emailService;
    private boolean checkpassword (String currentPassword,String newPassword,String confirmPassword) {	//KT mật khẩu
    	Users user = (Users) session.getAttribute("user");
		if(user.getPassword().equals(currentPassword)) {
			if(newPassword.equals(confirmPassword)) {
				return true;
			}else {
				System.out.println("Mật khẩu xác nhận không trùng khớp");
				return false;
			}
		}else {
			System.out.println("Mật khẩu hiện tại không đúng");
	    	return false;
		}
    }
    public String generateOTP() {				// Tạo mã OTP xác nhận email
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
    @GetMapping("/user/change-password")
    public String abc () {
    	return "User/change-password"; 
    }
    
	@PostMapping("/user/change-password")
	public String change(Model m, 
			@RequestParam("currentPassword") String currentPassword,
			@RequestParam("newPassword")String newPassword,
			@RequestParam("confirmPassword") String confirmPassword) {
		if(checkpassword(currentPassword,newPassword,confirmPassword)) {	
			session.setAttribute("newPassword", newPassword);
			session.setAttribute("otp",generateOTP());
			return "redirect:/send";
		}else {
			return "User/change-password";
		}
	}
	@RequestMapping("/verify")
	public String verify(Model m) {
		return "User/verify-email";
	}
	@PostMapping("/verify-get")	
	public String verify_get(Model m,@RequestParam("code")String code) {
		if(code.equals((String)session.getAttribute("otp"))){
			Users user = (Users)session.getAttribute("user");
			user.setPassword((String)session.getAttribute("newPassword"));
			userdao.save(user);
			session.setAttribute("user", user);
			session.removeAttribute("otp");
			session.removeAttribute("newPassword");
			return "redirect:/";
		}else {
			System.out.println("Mã xác nhận không đúng");
			return "redirect:/verify";
		}
	}
	
	@GetMapping("/send")
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
