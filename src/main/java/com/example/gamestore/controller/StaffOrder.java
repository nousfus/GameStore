package com.example.gamestore.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gamestore.dao.NotificationsDao;
import com.example.gamestore.dao.OrderDetailsDao;
import com.example.gamestore.dao.OrdersDao;
import com.example.gamestore.entity.Notifications;
import com.example.gamestore.entity.Orders;

@Controller
public class StaffOrder {
	@Autowired
	OrdersDao orderdao;
	@Autowired
	OrderDetailsDao orderdetaildao;
	@Autowired
	NotificationsDao notidao;
	
	
	@RequestMapping("/stafforders")
	public String list(Model m) {
		m.addAttribute("dsorder",orderdao.findAll());
		m.addAttribute("dsorderdetail",orderdetaildao.findAll());
		return "staff/staff";
	}
	@RequestMapping("/pending/{id}")
	public String pending(Model m, @PathVariable("id") String id) {
		Orders order = orderdao.findById(id).orElse(null);
		order.setStatus("Paid");
		orderdao.save(order);
		
		String username = "admin01";
		//Notification id
		List<Notifications> notilist = notidao.findAll();
		Notifications noti = notilist.get(notidao.findAll().size()-1);
		int last_no_id = Integer.parseInt(noti.getnotification_id().substring(4));
		String new_no_id = "NT00"+(last_no_id + 1);
		
		//notification content
		String title = "Mua hàng thành công";
		String content = "Xin chúc mừng đơn hàng "+order.getOrder_id()+" của bạn đã được mua thành công";
		Date date = new Date(System.currentTimeMillis());
		
		notidao.save(new Notifications(new_no_id,username,title,content,false,date));
		return "forward:/stafforders";
	}
}
