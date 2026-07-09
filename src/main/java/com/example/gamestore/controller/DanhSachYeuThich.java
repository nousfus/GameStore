package com.example.gamestore.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.gamestore.dao.CartDao;
import com.example.gamestore.dao.CartItemsDao;
import com.example.gamestore.dao.GameDao;
import com.example.gamestore.dao.OrderDetailsDao;
import com.example.gamestore.dao.WishListDao;
import com.example.gamestore.entity.Cart;
import com.example.gamestore.entity.CartItems;
import com.example.gamestore.entity.Game;
import com.example.gamestore.entity.OrderDetails;
import com.example.gamestore.entity.Users;
import com.example.gamestore.entity.WishList;

import jakarta.servlet.http.HttpSession;

@Controller
public class DanhSachYeuThich {

    @Autowired
    private WishListDao wishListDao;
    @Autowired
    OrderDetailsDao orderdetaildao;
    @Autowired
    CartDao cartdao;
    @Autowired
    CartItemsDao cartitemdao;
    @Autowired
    HttpSession session;
    @Autowired
    GameDao gamedao;
    // HIỂN THỊ DANH SÁCH YÊU THÍCH
    @GetMapping("/user/wishlist")
    public String wishlist(
            @RequestParam(defaultValue = "newest") String sort,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String message,
            
            Model model) {

        // Lấy user đang đăng nhập
        Users user = (Users) session.getAttribute("user");
        if(user!=null) {
			List<String> gamePaidId = new ArrayList<>();
			for(OrderDetails d : orderdetaildao.findAll()) {
				if(d.getOrder().getUsername().equals(user.getUsername()) && d.getOrder().getStatus().equals("Paid")) {
					gamePaidId.add(d.getGame().getGame_id());
				}
			}
			model.addAttribute("gamePaidId",gamePaidId);
			Cart cart = cartdao.findByUsername(user.getUsername());
			List<CartItems> cartitemlist = cartitemdao.findByCartId(cart.getCart_id());
			List<String> listgame = new ArrayList<>();
			for(CartItems c : cartitemlist) {
				listgame.add(c.getGame().getGame_id());
			}
			model.addAttribute("listgame",listgame);
			model.addAttribute("username",user);
		}
        // Chưa đăng nhập
        if (user == null) {
            return "redirect:/login";
        }

        // Chỉ lấy wishlist của user hiện tại
        List<WishList> danhSachWishlist = wishListDao.findByUsername(user.getUsername());

        // Sắp xếp
        switch (sort) {
            case "oldest":
                danhSachWishlist.sort(Comparator.comparing(WishList::getAdded_at));
                break;

            default:
                danhSachWishlist.sort(
                        Comparator.comparing(WishList::getAdded_at).reversed());
                break;
        }

        // Phân trang
        int itemsPerPage = 9;
        int totalPages = (int) Math.ceil((double) danhSachWishlist.size() / itemsPerPage);

        if (totalPages == 0) {
            totalPages = 1;
        }

        if (page < 1)
            page = 1;

        if (page > totalPages)
            page = totalPages;

        int start = (page - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, danhSachWishlist.size());

        List<WishList> pageData = danhSachWishlist.subList(start, end);

        model.addAttribute("wishlistList", pageData);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("sort", sort);
        model.addAttribute("message", message);

        return "User/wishlist";
    }
    @GetMapping("/user/wishlist/add/{id}")
    public String add(@PathVariable String id) {
    	Users user = (Users) session.getAttribute("user");
    	if(user==null) {
			return "redirect:/login-register";
		}
    	List<WishList> wishlist = wishListDao.findByUsername(user.getUsername());
    	List<String> gamewishlist = new ArrayList<>();
    	for(WishList w : wishlist) {
    		gamewishlist.add(w.getGame().getGame_id());
    	}
     	if(!gamewishlist.contains(id)) {
        	List<WishList> all = wishListDao.findAll();
        	WishList last = all.get(wishListDao.findAll().size()-1);
        	String wishList_id = "WL00" + (Integer.parseInt(last.getwishlist_id().substring(4)) + 1);
        	Date date = new Date(System.currentTimeMillis());
        	Game game = gamedao.findById(id).orElse(null);
    		wishListDao.save(new WishList(wishList_id,user.getUsername(),game,date));
    	}else {
    		for(WishList c : wishlist) {
    			if(c.getGame().getGame_id().equals(id)) {
    				wishListDao.delete(c);
    			}
    		}
    		
    	}
        String path = (String) session.getAttribute("path");
        return "redirect:"+path;
    }
    
    // XÓA DANH SÁCH YÊU THÍCH
    @PostMapping("/user/wishlist/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        wishListDao.deleteById(id);
        return "redirect:/user/wishlist?message=Đã+xóa+khỏi+danh+sách+yêu+thích";
    }

//    // THÊM VÀO GIỎ HÀNG
//    @PostMapping("/user/wishlist/addcart")
//    public String addCart(@RequestParam("id") String id) {
//    	
//        return "redirect:/user/wishlist?message=Đã+thêm+vào+giỏ+hàng";
//    }
}