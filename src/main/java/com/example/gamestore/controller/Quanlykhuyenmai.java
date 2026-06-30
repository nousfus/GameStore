package com.example.gamestore.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
// Test pull từ git hub
@Controller 
public class Quanlykhuyenmai {

    // 1. Hiển thị trang quản lý khuyến mãi
    @GetMapping("/admin/khuyenmai")
    public String khuyenmai(Model model){
        // TODO: Gọi Service để lấy danh sách khuyến mãi từ CSDL và đưa vào giao diện
        // List<Promotion> promotions = promotionService.findAll();
        // model.addAttribute("promotions", promotions);
        
        return "Admin/promotions";
    }

    // 2. Xử lý Thêm khuyến mãi
    @PostMapping("/admin/promotions/add")
    public String addPromotion(
            @RequestParam("code") String code,
            @RequestParam("type") String type,
            @RequestParam("value") Double value,
            @RequestParam("expiry") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate expiry,
            @RequestParam("games") String games) {
        
        // TODO: Gọi Service để lưu đối tượng khuyến mãi mới vào CSDL
        System.out.println("Thêm mã KM mới: " + code + ", Loại: " + type + ", Giá trị: " + value);
        
        // Sau khi thêm xong, chuyển hướng (redirect) về lại trang danh sách
        return "redirect:/admin/khuyenmai";
    }

    // 3. Xử lý Sửa khuyến mãi
    @PostMapping("/admin/promotions/edit")
    public String editPromotion(
            @RequestParam("code") String oldCode, // Mã cũ để tìm trong CSDL
            @RequestParam("code_new") String newCode,
            @RequestParam("type") String type,
            @RequestParam("value") Double value,
            @RequestParam("expiry") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate expiry,
            @RequestParam("games") String games) {
        
        // TODO: Gọi Service tìm khuyến mãi theo "oldCode" và cập nhật thông tin mới
        System.out.println("Cập nhật mã KM: " + oldCode + " -> Mã mới: " + newCode);
        
        return "redirect:/admin/khuyenmai";
    }

    // 4. Xử lý Xóa khuyến mãi
    @PostMapping("/admin/promotions/delete")
    public String deletePromotion(@RequestParam("code") String code) {
        
        // TODO: Gọi Service để xóa khuyến mãi theo mã code
        System.out.println("Xóa mã KM: " + code);
        
        return "redirect:/admin/khuyenmai";
    }
}