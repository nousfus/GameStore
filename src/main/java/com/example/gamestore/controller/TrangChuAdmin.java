package com.example.gamestore.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamestore.dao.CartDao;
import com.example.gamestore.dao.GameDao;
import com.example.gamestore.dao.OrdersDao;
import com.example.gamestore.dao.RolesDao;
import com.example.gamestore.dao.UserRolesDao;
import com.example.gamestore.entity.DeveloperProfiles;
import com.example.gamestore.entity.Game;
import com.example.gamestore.entity.OrderDetails;
import com.example.gamestore.entity.Orders;
import com.example.gamestore.entity.Roles;
import com.example.gamestore.entity.Users;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class TrangChuAdmin {

    private final CartDao cartDao;
	@Autowired
	HttpSession session;
	@Autowired
	UserRolesDao userroledao;
	@Autowired
	RolesDao roledao;
	@Autowired
	GameDao gamedao;
	@Autowired
	OrdersDao orderdao;
    TrangChuAdmin(CartDao cartDao) {
        this.cartDao = cartDao;
    }
	@RequestMapping("/home")
	public String main(Model m) {
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			m.addAttribute("username",user.getUsername());
		}
		return "Admin/home";
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

	@RequestMapping("/content-management")
	public String content(Model m) {
		m.addAttribute("listgame",gamedao.findAll());
		return "Admin/content-management";
	}
	@RequestMapping("/games/edit/{id}")
	public String edit (@PathVariable("id") String id) {
		Game game = gamedao.findById(id).orElse(null);
		game.setStatus("Active");
		gamedao.save(game);
		return "forward:/admin/content-management";
	}
	@PostMapping("/games/delete")
	public String delete(@RequestParam("id") String id) {
		Game game = gamedao.findById(id).orElse(null);
		gamedao.delete(game);
		return "forward:/admin/content-management";
	}
	@RequestMapping("/user-management")
	public String user() {
		return "redirect:/admin/cruduser";
	}
	@RequestMapping("/promotions")
	public String promotions() {
		return "redirect:/admin/khuyenmai";
	}
	@RequestMapping("/revenue-report")
	public String revenuereport(Model m,
			 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
		        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
		if(startDate == null){
		    startDate = LocalDate.now().minusDays(14);
		}

		if(endDate == null){
		    endDate = LocalDate.now();
		}
		
		double TongDoanhThu = 0;
		int DonHangHomNay = 0;
		Date current = new Date(System.currentTimeMillis());
		List<Orders> paidOrder = new ArrayList<>();
		for(Orders o : orderdao.findAll()) {
			TongDoanhThu += o.getTotal_amount();
			if(o.getOrder_date().equals(current)){
				DonHangHomNay += 1;
			}
			if(o.getStatus().equals("Paid")) {
				paidOrder.add(o);
			}
		}
		List<Object[]> data = orderdao.revenueByDate(startDate, endDate);

		Map<LocalDate, Double> revenueMap = new LinkedHashMap<>();

		for (Object[] row : data) {
			LocalDate date = (LocalDate) row[0];
			Double revenue = ((Number) row[1]).doubleValue();

		    revenueMap.put(date, revenue);
		}
		
		List<Double> revenues = new ArrayList<>();
		List<LocalDate> dates = new ArrayList<>();
		for(LocalDate d = startDate;
			    !d.isAfter(endDate);
			    d = d.plusDays(1)){

			    dates.add(d);
			    revenues.add(revenueMap.getOrDefault(d,0.0));
			}
		double maxRevenue = revenues.stream()
		        .mapToDouble(Double::doubleValue)
		        .max()
		        .orElse(1);
		
		double lonNhat = Double.MIN_VALUE;
		double nhoNhat = Double.MAX_VALUE;
		double tong = 0;
		int dem = 0;

		for (Double revenue : revenues) {

		    if (revenue == 0)
		        continue;

		    tong += revenue;
		    dem++;

		    if (revenue > lonNhat) {
		        lonNhat = revenue;
		    }

		    if (revenue < nhoNhat) {
		        nhoNhat = revenue;
		    }
		}

		double trungBinh = dem == 0 ? 0 : tong / dem;

		if (dem == 0) {
		    lonNhat = 0;
		    nhoNhat = 0;
		}
		m.addAttribute("lonNhat",lonNhat);
		m.addAttribute("nhoNhat",nhoNhat);
		m.addAttribute("trungBinh",trungBinh);
		m.addAttribute("maxRevenue", maxRevenue);
		m.addAttribute("revenues",revenues);
		m.addAttribute("dates",dates);
		m.addAttribute("TongDoanhThu",TongDoanhThu);
		m.addAttribute("TongDonHang",orderdao.findAll().size());
		m.addAttribute("DonHangHomNay",DonHangHomNay);
		m.addAttribute("listorder",paidOrder);
		return "Admin/revenue-report";
	}
}
