package com.example.gamestore.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gamestore.dao.CartDao;
import com.example.gamestore.dao.RolesDao;
import com.example.gamestore.dao.UserDao;
import com.example.gamestore.dao.UserRolesDao;
import com.example.gamestore.entity.Cart;
import com.example.gamestore.entity.Roles;
import com.example.gamestore.entity.UserRoles;
import com.example.gamestore.entity.Users;

@Controller
public class QuanLyUser {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private CartDao cartDao;
    
    @Autowired
    private RolesDao roleDao;
    
    @Autowired
    private UserRolesDao userroleDao;

    // 1. GIAO DIỆN CHÍNH (Đã bổ sung GET và truyền dữ liệu list sang Thymeleaf)
    @RequestMapping("/admin/cruduser")
    public String crud(Model model) {
        List<Users> danhSachUsers = userDao.findAll();
        model.addAttribute("usersList", danhSachUsers);
        
        return "Admin/user-management"; 
    }

    // 2. CHỨC NĂNG: THÊM NGƯỜI DÙNG
    @PostMapping("/admin/user-management/add")
    public String add(@RequestParam("name") String name, @RequestParam("email") String email, 
                      @RequestParam("status") String status, @RequestParam("role") String role) {
        String autoUsername = email.split("@")[0];
        if (userDao.existsById(autoUsername)) {
            autoUsername = autoUsername + "_" + (System.currentTimeMillis() % 1000);
        }
        
        Users newUser = new Users(autoUsername, email, "123456", name, "default.png", 
                                  new java.sql.Date(System.currentTimeMillis()), status);
        userDao.save(newUser);

        // Cấp quyền (Role)
        Roles r = roleDao.findByRoleName(role);
        if(r != null) {
            userroleDao.save(new UserRoles(autoUsername, r.getRoleId()));
        }

        // Cấp giỏ hàng (Cart)
        List<Cart> cartList = cartDao.findAll();
        String newCartId = "CART001";
        if(!cartList.isEmpty()){
            Cart lastCart = cartList.get(cartList.size()-1);
            int id = Integer.parseInt(lastCart.getCart_id().substring(6));
            newCartId = "CART00" + (id + 1);
        }
        cartDao.save(new Cart(newCartId, autoUsername, new java.sql.Date(System.currentTimeMillis()), "Open"));

        return "redirect:/admin/cruduser";
    }

    // 3. CHỨC NĂNG: SỬA NGƯỜI DÙNG
    @PostMapping("/admin/user-management/edit")
    public String edit(
            @RequestParam("id") String id,    
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
        Users user = userDao.findById(id).orElse(null);
        if (user != null) {
            // Khóa tài khoản thay vì xóa vật lý để giữ lại dữ liệu Đơn hàng
            user.setStatus("Banned");
            userDao.save(user);
        }
        return "redirect:/admin/cruduser";
    }
}