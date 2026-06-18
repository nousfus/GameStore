package com.example.gamestore.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamestore.dao.CartDao;
import com.example.gamestore.dao.CartItemsDao;
import com.example.gamestore.dao.DiscountsDao;
import com.example.gamestore.dao.GameDao;
import com.example.gamestore.dao.OrderDetailsDao;
import com.example.gamestore.dao.OrdersDao;
import com.example.gamestore.dao.PaymentsDao;
import com.example.gamestore.entity.CartItems;
import com.example.gamestore.entity.Discounts;
import com.example.gamestore.entity.Game;
import com.example.gamestore.entity.OrderDetails;
import com.example.gamestore.entity.Orders;
import com.example.gamestore.entity.Payments;
import com.example.gamestore.entity.Users;

import jakarta.servlet.http.HttpSession;

@Controller
public class ThanhToan {
	@Autowired
	HttpSession session;
	@Autowired
	OrdersDao orderdao;
	@Autowired
	OrderDetailsDao orderdetaildao;
	@Autowired
	PaymentsDao paymentdao;
	@Autowired
	CartDao cartdao;
	@Autowired
	CartItemsDao cartitemdao;
	@Autowired 
	GameDao gamedao;
	@Autowired
	DiscountsDao discountdao;
	@GetMapping("/payment/process")
	public String a(Model m) {
		Users user = (Users) session.getAttribute("user");
		m.addAttribute("username",user.getUsername());
		m.addAttribute("total",session.getAttribute("total"));
		return "User/payment";
	}
	@PostMapping("/payment/process")
	public String abc(Model m,@RequestParam("method") String pay) {
		Users user = (Users) session.getAttribute("user");
		String neworderid = null;
		for(Orders o : orderdao.findAll()) {
			int lastorder = Integer.parseInt(o.getOrder_id().substring(4));
			neworderid = "OD00"+(lastorder+1);									//Mã order
		}
		Date date = new Date(System.currentTimeMillis());
		
		String neededCart_id = (String) session.getAttribute("cartid");
		String tempusername = user.getUsername();									//Tổng giá sản phẩm
		int total =	(int)session.getAttribute("total");							
		Orders order = new Orders(neworderid,tempusername,date,total,"Pending");orderdao.save(order);	//tạo order
		
		
		// Order Detail
		List<OrderDetails> temp = orderdetaildao.findAll();
		OrderDetails od = temp.get(orderdetaildao.findAll().size()-1);
		int lastorder = Integer.parseInt(od.getOrder_detail_id().substring(5));
		String neworderdetailid = "ODD00"+(lastorder+1);									//Mã orderdetails
		
		
		for(CartItems c : cartitemdao.findByCartId(neededCart_id)) {
			Discounts discount = discountdao.findByGameId(c.getGame().getGame_id());					// Discount của game
			float discountamount = 0;
			if(discount!=null) {
				discountamount = (c.getGame().getPrice() * discount.getdiscount_percent()) / 100;
			}else {discountamount = 0;}
			OrderDetails odd = new OrderDetails(neworderdetailid,order,c.getGame().getGame_id(),c.getGame().getPrice(),discountamount);orderdetaildao.save(odd);	
			
		}

		//Payments
		List<Payments> hahaha = paymentdao.findAll();
		Payments pa = hahaha.get(paymentdao.findAll().size()-1);
		
		int payment = Integer.parseInt(pa.getPayment_id().substring(4));
		String newpaymentid = "PM00"+(payment+1);									//Mã payment id
		
		int lsattrancation = Integer.parseInt(pa.getTransactionCode().substring(7));
		String newtransactionid = "TRANS00"+(lsattrancation+1);									//Mã transaction
		
		Payments payments = new Payments(newpaymentid,neworderid,pay,"Success",date,newtransactionid);paymentdao.save(payments);
		for(CartItems c : cartitemdao.findAll()) {							// Xóa item trong cart item
			if(c.getCartid().equals(neededCart_id)) {
				cartitemdao.delete(c);
			}
		}
		cartdao.delete(cartdao.findById(neededCart_id).orElse(null));		// Xóa cart
		session.removeAttribute("cartid");
		session.removeAttribute("total");
		return "forward:/user/cart";
	}
}
