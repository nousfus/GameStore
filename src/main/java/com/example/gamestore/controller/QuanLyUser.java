package com.example.gamestore.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamestore.dao.UserDao;
import com.example.gamestore.entity.Users;

@Controller
public class QuanLyUser {

    @Autowired
    private UserDao userDao;

    // 1. GIAO DIỆN CHÍNH (Đã bổ sung GET và truyền dữ liệu list sang Thymeleaf)
    @RequestMapping("/admin/cruduser")
    public String crud(Model model) {
        List<Users> danhSachUsers = userDao.findAll();
        model.addAttribute("usersList", danhSachUsers);
        
        return "Admin/user-management"; 
    }

    // 2. CHỨC NĂNG: THÊM NGƯỜI DÙNG
    @PostMapping("/admin/user-management/add")
    public String add(
            @RequestParam("name") String name,       
            @RequestParam("email") String email,     
            @RequestParam("status") String status,   
            @RequestParam("role") String role) {     
        
        Users newUser = new Users();
        
        // Tự sinh username bằng phần trước chữ @ của email 
        String autoUsername = email.split("@")[0]; 
        if (userDao.existsById(autoUsername)) {
            autoUsername = autoUsername + "_" + (System.currentTimeMillis() % 1000);
        }

        // Đổ dữ liệu vào đúng các trường của Entity Users
        newUser.setUsername(autoUsername);
        newUser.setFullname(name); 
        newUser.setEmail(email);
        newUser.setStatus(status);
        newUser.setPassword("123456"); // Mật khẩu mặc định
        newUser.setAvatar("default.png"); // Avatar mặc định
        newUser.setCreatedDate(new java.sql.Date(System.currentTimeMillis())); // Ngày tạo hiện tại

        // Lưu vào database
        userDao.save(newUser);
        
        // Quay trở lại trang danh sách để thấy user mới
        return "redirect:/admin/cruduser";
    }

    // 3. CHỨC NĂNG: SỬA NGƯỜI DÙNG
    @PostMapping("/admin/user-management/edit")
    public String edit(
            @RequestParam("id") String id,           // Nhận vào username (được gán vào thuộc tính name="id" trên HTML)
            @RequestParam("name") String name,       
            @RequestParam("email") String email,     
            @RequestParam("status") String status,   
            @RequestParam("role") String role) {     
        
        // Tìm user cũ từ Database lên bằng Id (chính là username)
        Users existingUser = userDao.findById(id).orElse(null);
        
        if (existingUser != null) {
            // Cập nhật các trường thông tin thay đổi
            existingUser.setFullname(name);
            existingUser.setEmail(email);
            existingUser.setStatus(status);
            
            // Lưu đè (Spring Data JPA tự hiểu là Update nếu trùng Khóa chính)
            userDao.save(existingUser);
        }
        
        return "redirect:/admin/cruduser";
    }

    // 4. CHỨC NĂNG: XÓA NGƯỜI DÙNG
    @PostMapping("/admin/user-management/delete")
    public String delete(@RequestParam("id") String id) {
        if (userDao.existsById(id)) {
            userDao.deleteById(id);
        }
        return "redirect:/admin/cruduser";
    }
}