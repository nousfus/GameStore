package com.example.gamestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@RequestMapping("user/notification")	
	public String abc(Model m) {
		Users user = (Users) session.getAttribute("user");
		m.addAttribute("username",user.getUsername());
		m.addAttribute("notifications",notificationdao.findByUsername(user.getUsername()));
		return "User/Notification";
	}
	@GetMapping("/user/notifications/mark-read/{id}")
	public String mark(@PathVariable("id") String id) {
		Notifications noti = notificationdao.findById(id).orElse(null);
		noti.setRead(true);
		notificationdao.save(noti);
		return "redirect:/user/notification";
	}
	@GetMapping("/user/notifications/delete/{id}")
	public String delete(@PathVariable("id") String id) {
		Notifications noti = notificationdao.findById(id).orElse(null);
		notificationdao.delete(noti);
		return "redirect:/user/notification";
	}
}
