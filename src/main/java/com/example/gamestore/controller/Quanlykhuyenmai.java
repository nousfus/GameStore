package com.example.gamestore.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamestore.dao.DiscountsDao;
import com.example.gamestore.dao.GameDao;
import com.example.gamestore.entity.Discounts;
// Test pull từ git hub
@Controller 
public class Quanlykhuyenmai {
	@Autowired
	DiscountsDao discountdao;
	@Autowired
	GameDao gamedao;
    // 1. Hiển thị trang quản lý khuyến mãi
    @GetMapping("/admin/khuyenmai")
    public String khuyenmai(Model model){
        // TODO: Gọi Service để lấy danh sách khuyến mãi từ CSDL và đưa vào giao diện
         List<Discounts> promotions = discountdao.findAll();
         model.addAttribute("gamelist",gamedao.findAll());
         model.addAttribute("promotions", promotions);
         model.addAttribute("Edit",new Discounts());
         model.addAttribute("isEdit",false);
        return "Admin/promotions";
    }

    // 2. Xử lý Thêm khuyến mãi
    @PostMapping("/admin/promotions/add")
    public String addPromotion(
            @RequestParam("code") String code,
            @RequestParam("type") String type,
            @RequestParam("value") float value,
            @RequestParam("expiry") Date expiry,
            @RequestParam("games") String games) {
        
        		Date current = new Date(System.currentTimeMillis());
        	discountdao.save(new Discounts(code,games,value,current,expiry,"Active",type));
        return "redirect:/admin/khuyenmai";
    }
    @RequestMapping("/admin/promotions/edit/{id}")
    public String edit(Model m,@PathVariable("id") String id) {
    	List<Discounts> promotions = discountdao.findAll();
        m.addAttribute("promotions", promotions);
    	Discounts discount = discountdao.findById(id).orElse(null);
    	m.addAttribute("Edit",discount);
    	m.addAttribute("isEdit",true);
    	m.addAttribute("gamelist",gamedao.findAll());
    	return "Admin/promotions";
    }

    // 4. Xử lý Xóa khuyến mãi
    @PostMapping("/admin/promotions/delete")
    public String deletePromotion(@RequestParam("code") String code) {
       discountdao.deleteById(code);
        return "redirect:/admin/khuyenmai";
    }
}