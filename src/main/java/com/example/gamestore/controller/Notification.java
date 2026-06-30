package com.example.gamestore.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.gamestore.dao.NotificationsDao;
import com.example.gamestore.entity.Notifications;
import com.example.gamestore.entity.Users;

import jakarta.servlet.http.HttpSession;

@Controller
public class Notification {

	@Autowired
	HttpSession session;

	@Autowired
	NotificationsDao notificationdao;

	// =========================================================================
	// PHÂN HỆ: USER (XEM, ĐỌC, XÓA THÔNG BÁO)
	// =========================================================================

	@RequestMapping("user/notification")	
	public String abc(Model m) {
		Users user = (Users) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login"; 
		}
		m.addAttribute("username", user.getUsername());
		m.addAttribute("notifications", notificationdao.findByUsername(user.getUsername()));
		return "User/Notification";
	}

	@GetMapping("/user/notifications/mark-read/{id}")
	public String mark(@PathVariable("id") String id) {
		Notifications noti = notificationdao.findById(id).orElse(null);
		if (noti != null) {
			noti.setRead(true);
			notificationdao.save(noti);
		}
		return "redirect:/user/notification";
	}

	@GetMapping("/user/notifications/delete/{id}")
	public String delete(@PathVariable("id") String id) {
		if (notificationdao.existsById(id)) {
			notificationdao.deleteById(id);
		}
		return "redirect:/user/notification";
	}

	// =========================================================================
	// PHÂN HỆ: STAFF (SOẠN, GỬI VÀ XEM LỊCH SỬ THÔNG BÁO)
	// =========================================================================

	/**
	 * Hiển thị giao diện gửi thông báo kèm danh sách tất cả thông báo đã gửi
	 */
	@GetMapping("/staff/notification")
	public String showStaffForm(Model m) {
		// BỔ SUNG DÒNG NÀY: Lấy toàn bộ danh sách thông báo từ DB đưa vào Model
		m.addAttribute("allNotifications", notificationdao.findAll());
		
		// Trả về đúng file HTML theo cấu trúc thư mục của bạn
		return "staff/notifications"; 
	}

	/**
	 * Xử lý gửi thông báo
	 */
	@PostMapping("/staff/notification/send")
	public String sendNotification(
			@RequestParam("username") String username,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			RedirectAttributes param) {
		
		try {
			Notifications newNoti = new Notifications();
			
			String uniqueId = UUID.randomUUID().toString().substring(0, 8);
			newNoti.setNotification_id(uniqueId);
			
			newNoti.setUsername(username);
			newNoti.setTitle(title);
			newNoti.setContent(content);
			newNoti.setRead(false);
			newNoti.setCreated_at(new Date());
			
			notificationdao.save(newNoti);
			
			param.addFlashAttribute("message", "🎉 Gửi thông báo thành công!");
			
		} catch (Exception e) {
			e.printStackTrace();
			param.addFlashAttribute("error", "❌ Gửi thất bại! Vui lòng kiểm tra lại hệ thống.");
		}
		
		return "redirect:/staff/notification";
	}
}