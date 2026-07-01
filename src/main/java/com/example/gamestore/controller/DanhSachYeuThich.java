package com.example.gamestore.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.gamestore.dao.WishListDao;
import com.example.gamestore.entity.WishList;

@Controller
public class DanhSachYeuThich {

    @Autowired
    private WishListDao wishListDao;

 // HIỂN THỊ DANH SÁCH YÊU THÍCH + SẮP XẾP + PHÂN TRANG
    @GetMapping("/user/wishlist")
    public String wishlist(
            @RequestParam(defaultValue = "newest") String sort,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String message,
            Model model) {

    	// Lấy toàn bộ danh sách Wishlist từ Database
        List<WishList> danhSachWishlist = wishListDao.findAll();

        // Sắp xếp bộ lọc
        switch (sort) {
            case "newest":
                danhSachWishlist.sort(Comparator.comparing(WishList::getadded_at).reversed());
                break;
            case "oldest":
                danhSachWishlist.sort(Comparator.comparing(WishList::getadded_at));
                break;
        }
        
        // Phân trang
        int itemsPerPage = 9;
        int totalPages = (int) Math.ceil((double) danhSachWishlist.size() / itemsPerPage);

        if (page < 1) page = 1;
        if (page > totalPages && totalPages > 0) page = totalPages;

        int start = (page - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, danhSachWishlist.size());

        List<WishList> pageData = danhSachWishlist.subList(start, end);

        // Đẩy dữ liệu sang HTML
        model.addAttribute("wishlistList", pageData);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("sort", sort);
        model.addAttribute("message", message);

        return "User/wishlist";
    }

    // XÓA KHỎI DANH SÁCH YÊU THÍCH
    @PostMapping("/user/wishlist/delete")
    public String delete(@RequestParam("id") String id) {
        wishListDao.deleteById(id);
        return "redirect:/user/wishlist?message=Đã+xóa+khỏi+danh+sách+yêu+thích";
    }

    // THÊM VÀO GIỎ HÀNG
    @PostMapping("/user/wishlist/addcart")
    public String addCart(@RequestParam("id") String id) {
        // Thêm game vào bảng Cart
        return "redirect:/user/wishlist?message=Đã+thêm+vào+giỏ+hàng";
    }
}