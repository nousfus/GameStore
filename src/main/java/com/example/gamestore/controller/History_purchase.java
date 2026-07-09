package com.example.gamestore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamestore.dao.OrderDetailsDao;
import com.example.gamestore.dao.OrdersDao;
import com.example.gamestore.dao.PaymentsDao;
import com.example.gamestore.entity.OrderDetails;
import com.example.gamestore.entity.Orders;
import com.example.gamestore.entity.Payments;
import com.example.gamestore.entity.Users;

import jakarta.servlet.http.HttpSession;

@Controller
public class History_purchase {
	@Autowired
	HttpSession session;
	@Autowired
	OrderDetailsDao orderdetaildao;
	@Autowired
	OrdersDao orderdao;
	@Autowired
	PaymentsDao paymentdao;
	@RequestMapping("user/history")
	public String history(Model m,@RequestParam(required = false) String status) {
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			m.addAttribute("username",user.getUsername());
		    List<Orders> dsorder;

		    if(status == null || status.isEmpty()) {
		        dsorder = orderdao.findByUsername(user.getUsername());
		    } else {
		        dsorder = orderdao.findByUsernameAndStatus(user.getUsername(),status);
		    }

		    m.addAttribute("dsorder", dsorder);
			m.addAttribute("orderdetaildao", orderdetaildao);
		}
		return "User/purchase-history";
	}
	@RequestMapping("/user/cancel/{id}")
	public String cancel(@PathVariable("id") String id) {
		Orders order = orderdao.findById(id).orElse(null);
		Payments payment = paymentdao.findByOrderid(id);
		List<OrderDetails> odetail = orderdetaildao.findByOrderID(id);
		for(OrderDetails d : odetail) {
			orderdetaildao.delete(d);
		}
		paymentdao.delete(payment);
		order.setStatus("Cancelled");
		orderdao.save(order);
		return "User/purchase-history";
	}
}