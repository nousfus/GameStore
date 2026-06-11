package com.example.gamestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gamestore.dao.OrderDetailsDao;
import com.example.gamestore.dao.OrdersDao;
import com.example.gamestore.entity.OrderDetails;
import com.example.gamestore.entity.Orders;
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
	@RequestMapping("/user/history-purchase")
	public String history(Model m) {
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			m.addAttribute("username",user.getUsername());
			System.out.print(user.getUsername());
			m.addAttribute("orderdetaildao", orderdetaildao);
			m.addAttribute("dsorder",orderdao.findByUsername(user.getUsername()));
		}
		return "User/purchase-history";
	}
}

//  th:each = "itemsDetail :${orderdetaildao.findByOrderID(item.orderId)}"