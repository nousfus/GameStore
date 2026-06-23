package com.example.gamestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gamestore.dao.CartDao;
import com.example.gamestore.dao.CartItemsDao;
import com.example.gamestore.dao.DiscountsDao;
import com.example.gamestore.entity.Cart;
import com.example.gamestore.entity.CartItems;
import com.example.gamestore.entity.Discounts;
import com.example.gamestore.entity.Users;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserCart {
	@Autowired
	HttpSession session;
	@Autowired
	CartDao cartdao;
	@Autowired
	CartItemsDao cartitemdao;
	@Autowired
	DiscountsDao discountdao;
	@RequestMapping("/user/cart")
	public String cart(Model m) {
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			m.addAttribute("username",user.getUsername());
			Cart cart = cartdao.findByUsername(user.getUsername());
			List<CartItems> list0 = cartitemdao.findByCartId(cart.getCart_id());
			m.addAttribute("cartitem", list0);
			float total = 0;
			float discount = 0;
			for(CartItems c : list0) {
				total += c.getGame().getPrice() * c.getQuantity();
				for(Discounts d : discountdao.findAll()) {
					if(c.getGame().getGame_id().equals(d.getgame_id())) {
						discount += ((c.getGame().getPrice()* d.getdiscount_percent()) / 100 );
					}
				}
			}
			m.addAttribute("discount",discount);
			m.addAttribute("total",total);
			m.addAttribute("cartitemdao",cartitemdao);
		}
		return "User/cart";
	}
	@GetMapping("/user/cartitem/{id}")
	public String cartitem(Model m,
	        @PathVariable("id") String id) {
		Users user = (Users) session.getAttribute("user");
		Cart CartCount = cartdao.findByUsername(user.getUsername());
		session.removeAttribute("list0");
		session.setAttribute("cartid", id);													// Cart để thanh toán
	    List<CartItems> list = cartitemdao.findByCartId(id);
	    m.addAttribute("cartitem", list);
		int total = 0;
		float discount = 0;
		for(CartItems c : list) {
			total += c.getGame().getPrice() * c.getQuantity();
			for(Discounts d : discountdao.findAll()) {
				if(c.getGame().getGame_id().equals(d.getgame_id())) {
					discount += ((c.getGame().getPrice()* d.getdiscount_percent()) / 100 );
				}
			}
		}
		m.addAttribute("discount",discount);
		m.addAttribute("total",total);
		session.setAttribute("total", total);
	    return "forward:/user/cart";
	}
	@RequestMapping("/thanhtoan")
	public String thanhtoan(Model m) {
		Users user = (Users) session.getAttribute("user");
		m.addAttribute("total",session.getAttribute("total"));
		m.addAttribute("username",user.getUsername());
		return "User/payment";
	}
}
