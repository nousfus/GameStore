package com.example.gamestore.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamestore.dao.*;
import com.example.gamestore.entity.Cart;
import com.example.gamestore.entity.CartItems;
import com.example.gamestore.entity.Discounts;
import com.example.gamestore.entity.Game;
import com.example.gamestore.entity.OrderDetails;
import com.example.gamestore.entity.Orders;
import com.example.gamestore.entity.Payments;

@Controller
public class MuaHang {
	@Autowired
	GameDao gamedao;
	@Autowired
	CartDao cartdao;
	@Autowired
	CartItemsDao cartitemsdao;
	@Autowired
	OrdersDao orderdao;
	@Autowired
	OrderDetailsDao orderdetaildao;
	@Autowired
	PaymentsDao paymentdao;
	@Autowired
	DiscountsDao discountdao;

	@RequestMapping("/list")
	public String list(Model m) {
		m.addAttribute("dsgame",gamedao.findAll());					// Hiên thị danh sách game
		m.addAttribute("dscart",cartdao.findAll());					// Hiên thị danh sách cart
		m.addAttribute("dsorder",orderdao.findAll());				// Hiển thị danh sách order
		m.addAttribute("dsorderdetail",orderdetaildao.findAll());	// Hiển thị danh sách order detail
		m.addAttribute("dspayment",paymentdao.findAll());			// Hiển thị danh sách payments
		
		List<CartItems> list = new ArrayList<>();					
		List<CartItems> cartitems = cartitemsdao.findAll();
		int total = 0;
		Cart cart = cartdao.findByUsername("admin01");				// Lấy cartid của admin01
		for(CartItems c : cartitemsdao.findAll()) {
			if(c.getCart_id().equals(cart.getCart_id())) {
				Game game = gamedao.findById(c.getGame_id()).orElse(null);
				CartItems abc = new CartItems(c.getCart_item_id(),c.getCart_id(),game.getGame_name(),c.getQuantity());
				total += game.getPrice() * c.getQuantity();
				list.add(abc);
			}
		}
		m.addAttribute("carttotal",total);
		m.addAttribute("dscartitem",list);		// Hiên thị danh sách cartitems ( Tạo List mới để thay idgame thành tên game)
		return "MuaHang";
	}
	@PostMapping("/addToCart/{id}")
	public String addToCart(Model m, @PathVariable("id")String id) {
		Game game = gamedao.findById(id).orElse(null);
		Cart a = cartdao.findByUsername("admin01");						// Tìm kiếm cart theo username
		
		List<Cart> newcart = cartdao.findAll();
		Cart lastCart = newcart.get(cartdao.findAll().size()-1);
		int lastcartindex = Integer.parseInt(lastCart.getCart_id().substring(6));
		String newcart_id = "CART00"+(lastcartindex+1);
		
		Date date = new Date(System.currentTimeMillis());			// Lấy ngày tháng năm hiện tại
		int total = 0;
		if(a==null) {// Tạo Cart mới
			Cart cart = new Cart(newcart_id,"admin01",date,"Open") ;cartdao.save(cart);		
			// tạo CartItems mới
			String newcartitem_id = null;
			List<CartItems> abc = cartitemsdao.findAll();
			if(cartitemsdao.findAll().size()!=0) {
				CartItems cart_item = abc.get(cartitemsdao.findAll().size()-1);
				int lastcart = Integer.parseInt(cart_item.getCart_item_id().substring(4));
				 newcartitem_id = newcartitem_id = "CI00"+(lastcart+1);
			}else {
				 newcartitem_id = newcartitem_id = "CI00"+1;
			}
			CartItems cartitem = new CartItems(newcartitem_id,cart.getCart_id(),game.getGame_id(),1); cartitemsdao.save(cartitem);
		}else {
			CartItems cartitembyGame = cartitemsdao.findByGameId(id);
			if(cartitembyGame!=null) {	// Nếu đã có game trong CartItems
				CartItems cartitem = new CartItems(cartitembyGame.getCart_item_id(),a.getCart_id(),game.getGame_id(),cartitembyGame.getQuantity()+1); cartitemsdao.save(cartitem);
			}else {	
				String newcartitem_id = null;
				List<CartItems> abc = cartitemsdao.findAll();
				if(cartitemsdao.findAll().size()!=0) {
					CartItems cart_item = abc.get(cartitemsdao.findAll().size()-1);
					int lastcart = Integer.parseInt(cart_item.getCart_item_id().substring(4));
					 newcartitem_id = newcartitem_id = "CI00"+(lastcart+1);
				}else {
					 newcartitem_id = newcartitem_id = "CI00"+1;
				}
				CartItems cartitem = new CartItems(newcartitem_id,a.getCart_id(),game.getGame_id(),1); cartitemsdao.save(cartitem);
			}
		}
		return "forward:/list";
	}
	@GetMapping("/update")
	public String update(Model m,@RequestParam(value = "quantity") int quantity,
									@RequestParam("cartItemId") String cartitemid) {
		CartItems a = cartitemsdao.findById(cartitemid).orElse(null);
		if(a.getQuantity()==0) {
			cartitemsdao.delete(a);
		}else {
			a.setQuantity(quantity);
			cartitemsdao.save(a);
		}
		return "forward:/list";
	}
	@PostMapping("/delete/{abc}")
	public String dleete(@PathVariable("abc")String id) {
		CartItems cart = cartitemsdao.findById(id).orElse(null);
		cartitemsdao.delete(cart);
		return "redirect:/list";
	}
	@PostMapping("/checkout")
	public String checkout(@RequestParam("pay") String pay, Model m) {
		String neworderid = null;
		for(Orders o : orderdao.findAll()) {
			int lastorder = Integer.parseInt(o.getOrder_id().substring(4));
			neworderid = "OD00"+(lastorder+1);									//Mã order
		}
		Date date = new Date(System.currentTimeMillis());
		
		String neededCart_id = "CART001";	//cart mẫu
		String tempusername = "admin01"; 	//username mẫu
		int total = 0;														//Tổng giá sản phẩm
		Game game = new Game();
		for(CartItems c : cartitemsdao.findAll()) {
			if(c.getCart_id().equals(neededCart_id)) {
				game = gamedao.findById(c.getGame_id()).orElse(null);
				total += game.getPrice() * c.getQuantity();
			}
		}
		Orders order = new Orders(neworderid,tempusername,date,total,"Pending");orderdao.save(order);	//tạo order
		
		List<OrderDetails> temp = orderdetaildao.findAll();
		OrderDetails od = temp.get(orderdetaildao.findAll().size()-1);
		int lastorder = Integer.parseInt(od.getOrder_detail_id().substring(5));
		String neworderdetailid = "ODD00"+(lastorder+1);									//Mã orderdetails
		
		Discounts discount = discountdao.findByGameId(game.getGame_id());					// Discount của game
		float discountamount = 0;
		if(discount!=null) {
			discountamount = (game.getPrice() * discount.getdiscount_percent()) / 100;
		}else {discountamount = 0;}
		OrderDetails odd = new OrderDetails(neworderdetailid,neworderid,game.getGame_id(),game.getPrice(),discountamount);orderdetaildao.save(odd);	
		
		
		//Payments
		List<Payments> hahaha = paymentdao.findAll();
		Payments pa = hahaha.get(paymentdao.findAll().size()-1);
		
		int payment = Integer.parseInt(pa.getPayment_id().substring(4));
		String newpaymentid = "PM00"+(payment+1);									//Mã payment id
		
		int lsattrancation = Integer.parseInt(pa.getTransactionCode().substring(7));
		String newtransactionid = "TRANS00"+(lsattrancation+1);									//Mã transaction
		
		Payments payments = new Payments(newpaymentid,neworderid,pay,"Success",date,newtransactionid);paymentdao.save(payments);
		
		for(CartItems c : cartitemsdao.findAll()) {
			if(c.getCart_id().equals("neededCart_id")) {
				cartitemsdao.delete(c);
			}
		}
		return "forward:/list";
	}
}
