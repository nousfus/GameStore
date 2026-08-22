package com.example.gamestore.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		m.addAttribute("qrUrl","https://img.vietqr.io/image/VCB-9908208692-compact2.png\r\n"
				+ "    ?amount="+session.getAttribute("total2")+"\r\n"
				+ "    &addInfo=ORDER123\r\n"
				+ "    &accountName=LE%20HOANG%20THIEN%20TRIEU");
		
		m.addAttribute("username",user.getUsername());
		m.addAttribute("total",session.getAttribute("total2"));
		session.setAttribute("instant", false);
		return "User/payment";
	}
	@GetMapping("/user/instant-purchase/{gameid}")
	public String instantPurchase(
	        @PathVariable String gameid,
	        @RequestParam float total,Model m){
		System.out.println(gameid);
		Users user = (Users) session.getAttribute("user");
		m.addAttribute("username",user.getUsername());
		m.addAttribute("total",total);
		session.setAttribute("instantgame", gamedao.findById(gameid).orElse(null));
		session.setAttribute("instanttotal", total);
		session.setAttribute("instant", true);
		return "User/payment";
	}
	@PostMapping("/payment/process")
	public String abc(Model m,@RequestParam("method") String pay) {
		Users user = (Users) session.getAttribute("user");
		Boolean instant = (Boolean) session.getAttribute("instant");
		String neworderid = null;
		for(Orders o : orderdao.findAll()) {
			int lastorder = Integer.parseInt(o.getOrder_id().substring(4));
			neworderid = "OD00"+(lastorder+1);									//Mã order
		}
		Date date = new Date(System.currentTimeMillis());
		
		
		String neededCart_id = cartdao.findByUsername(user.getUsername()).getCart_id();
		String tempusername = user.getUsername();									//Tổng giá sản phẩm
		Float sessionTotal = instant ? (Float) session.getAttribute("instanttotal") : (Float) session.getAttribute("total2");
		float total = 0f;
		if (sessionTotal != null) {
		    total = sessionTotal;
		} else {
		    return "redirect:/user/cart"; 
		}						
		Orders order = new Orders(neworderid, tempusername, date, total, "Paid"); 
		orderdao.save(order);
		
		
		// Order Detail
		List<OrderDetails> temp = orderdetaildao.findAll();
		OrderDetails od = temp.get(orderdetaildao.findAll().size()-1);
		int lastorder = Integer.parseInt(od.getOrder_detail_id().substring(5));
		String neworderdetailid = "ODD00"+(lastorder+1);									//Mã orderdetails
		
		if(instant) {
			Game c = (Game) session.getAttribute("instantgame");
			Discounts discount = discountdao.findByGameId(c.getGame_id());					// Discount của game
			float discountamount = 0;
			if(discount!=null) {
				discountamount = discount.getDiscountType().equals("PERCENT") ? (c.getPrice() * discount.getDiscount_percent()) / 100 : discount.getDiscount_percent();
			}else {discountamount = 0;}
			OrderDetails odd = new OrderDetails(neworderdetailid,order,c,c.getPrice(),discountamount);orderdetaildao.save(odd);
		}else if(session.getAttribute("instantgame") == null &&  session.getAttribute("instant") == null) {
			for(CartItems c : cartitemdao.findByCartId(neededCart_id)) {
				Discounts discount = discountdao.findByGameId(c.getGame().getGame_id());					// Discount của game
				float discountamount = 0;
				if(discount!=null) {
					discountamount = (c.getGame().getPrice() * discount.getDiscount_percent()) / 100;
				}else {discountamount = 0;}
				OrderDetails odd = new OrderDetails(neworderdetailid,order,c.getGame(),c.getGame().getPrice(),discountamount);orderdetaildao.save(odd);	
			}
		}
		
		//Payments
		List<Payments> hahaha = paymentdao.findAll();
		String newpaymentid = "PM001";
		String newtransactionid = "TRANS001";
		
		if (hahaha != null && !hahaha.isEmpty()) {
		    Payments pa = hahaha.get(hahaha.size() - 1);
		    if (pa.getPayment_id() != null && pa.getPayment_id().length() > 4) {
		        int payment = Integer.parseInt(pa.getPayment_id().substring(4));
		        newpaymentid = "PM00" + (payment + 1);
		    }
		    if (pa.getTransaction_code() != null && pa.getTransaction_code().length() > 7) {
		        int lsattrancation = Integer.parseInt(pa.getTransaction_code().substring(7));
		        newtransactionid = "TRANS00" + (lsattrancation + 1);
		    }
		}
		
		Payments payments = new Payments(newpaymentid, neworderid, pay, "Success", date, newtransactionid);
		paymentdao.save(payments);
		
		for(CartItems c : cartitemdao.findAll()) {	
			if(c.getCartid().equals(neededCart_id)) {
				cartitemdao.delete(c);
			}
		}
		session.removeAttribute("cartid");
		session.removeAttribute("total");
		session.removeAttribute("total2");
		session.removeAttribute("quantity");
		session.removeAttribute("instantgame");
		session.removeAttribute("instanttotal");
		return "redirect:/payment/success/" + neworderid;
	}
	
	@GetMapping("/payment/success/{orderId}")
	public String paymentSuccess(@PathVariable("orderId") String orderId, Model m) {
	    Users user = (Users) session.getAttribute("user");
	    if (user == null) {
	        return "redirect:/login-register";
	    }

	    // Lấy thông tin đơn hàng, chi tiết sản phẩm và thanh toán từ Database
	    Orders order = orderdao.findById(orderId).orElse(null);
	    List<OrderDetails> orderDetails = orderdetaildao.findByOrderID(orderId);
	    Payments payment = paymentdao.findByOrderid(orderId);

	    // Kiểm tra xem đơn hàng có tồn tại và đúng của user hiện tại không
	    if (order == null || !order.getUsername().equals(user.getUsername())) {
	        return "redirect:/user/product-list";
	    }

	    m.addAttribute("order", order);
	    m.addAttribute("orderDetails", orderDetails);
	    m.addAttribute("payment", payment);

	    return "User/payment-success";
	}
}
