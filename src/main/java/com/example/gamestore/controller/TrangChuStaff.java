package com.example.gamestore.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamestore.dao.NotificationsDao;
import com.example.gamestore.dao.OrderDetailsDao;
import com.example.gamestore.dao.OrdersDao;
import com.example.gamestore.dao.PaymentsDao;
import com.example.gamestore.dao.RolesDao;
import com.example.gamestore.dao.UserRolesDao;
import com.example.gamestore.entity.Notifications;
import com.example.gamestore.entity.OrderDetails;
import com.example.gamestore.entity.Orders;
import com.example.gamestore.entity.Payments;
import com.example.gamestore.entity.Roles;
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
	@Autowired
	OrdersDao orderdao;
	@Autowired
	OrderDetailsDao orderdetaildao;
	@Autowired
	PaymentsDao paymentdao;
	@Autowired
	NotificationsDao notidao;
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
			m.addAttribute("rolepicked",session.getAttribute("rolepicked"));
			m.addAttribute("username",user.getUsername());
			m.addAttribute("user",user);
			List<Roles> list = userroledao.findByUsername(user.getUsername());
			m.addAttribute("dsrole",list);
		}
		return "User/profile";
	}
	@RequestMapping("/notifications")
	public String notification() {
		return "staff/notifications";
	}
	@RequestMapping("/orders")
	public String orders(Model m,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
	        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		if(startDate == null){
		    startDate = LocalDate.now().minusDays(14);
		}
		if(endDate == null){
		    endDate = LocalDate.now();
		}
		List<Orders> list  = orderdao.findAll();
		int TongDonHomNay = 0;
		int ChoXuLy = 0;
		int DaThanhToan =0;
		Date date = new Date(System.currentTimeMillis());
		for(Orders o : list) {
			if(o.getOrder_date().equals(date)) {
				TongDonHomNay++;
			}
			if(o.getStatus().equals("Pending")) {
				ChoXuLy++;
			}
			if(o.getStatus().equals("Paid")) {
				DaThanhToan++;	
			}
		}
		m.addAttribute("startDate",startDate);
		m.addAttribute("endDate",endDate);
		m.addAttribute("TongDonHang",list.size());
		m.addAttribute("TongDonHomNay",TongDonHomNay);
		m.addAttribute("ChoXuLy",ChoXuLy);
		m.addAttribute("DaThanhToan",DaThanhToan);
		m.addAttribute("listorder",list);

		return "staff/orders-management";
	}
	@RequestMapping("/orders-detail/{id}")
	public String ordersdetai(Model m,@PathVariable("id") String orderid) {
		Orders order = orderdao.findById(orderid).orElse(null);
		List<OrderDetails> orderdetail = orderdetaildao.findByOrderID(orderid);
		Payments payment = paymentdao.findByOrderid(orderid);
		
		m.addAttribute("payment",payment);
		m.addAttribute("order",order);
		m.addAttribute("orderdetail",orderdetail);
		return "staff/order-detail";
	}
	@PostMapping("/orders-update/{id}")
	public String update(Model m,@PathVariable("id") String orderid) {
		Users user = (Users) session.getAttribute("user");
		Orders order = orderdao.findById(orderid).orElse(null);
		List<OrderDetails> orderdetail = orderdetaildao.findByOrderID(orderid);
		order.setStatus("Paid");
		orderdao.save(order);
		return "redirect:/staff/orders";
	}
	@RequestMapping("/reviews")
	public String reviews() {
		return "staff/reviews-management";
	}
}
