package com.example.gamestore.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.gamestore.dao.CartDao;
import com.example.gamestore.dao.CartItemsDao;
import com.example.gamestore.dao.CategoriesDao;
import com.example.gamestore.dao.GameCategoriesDao;
import com.example.gamestore.dao.GameDao;
import com.example.gamestore.dao.OrdersDao;
import com.example.gamestore.dao.RolesDao;
import com.example.gamestore.dao.UserDao;
import com.example.gamestore.dao.UserRolesDao;
import com.example.gamestore.entity.Cart;
import com.example.gamestore.entity.CartItems;
import com.example.gamestore.entity.Categories;
import com.example.gamestore.entity.Game;
import com.example.gamestore.entity.GameCategories;
import com.example.gamestore.entity.Orders;
import com.example.gamestore.entity.Roles;
import com.example.gamestore.entity.Users;
import com.example.gamestore.service.MinioService;

import io.minio.MinioClient;
import jakarta.servlet.http.HttpSession;

@Controller
public class TrangChu {
	@Autowired
	HttpSession session;
	@Autowired
	UserDao userdao;
	@Autowired
	CartDao cartdao;
	@Autowired
	CartItemsDao cartitemdao;
	@Autowired
	UserRolesDao userroledao;
	@Autowired
	RolesDao roledao;
	@Autowired
	CategoriesDao categorydao;
	@Autowired
	GameDao gamedao;
	@Autowired
	GameCategoriesDao gamecategorydao;
	@Autowired
	OrdersDao orderdao;
	@Autowired
	private MinioClient minioClient;
	@Autowired
	MinioService minioService;
	@RequestMapping("/")
	public String abc(Model m) {
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			m.addAttribute("username",user.getUsername());
			session.setAttribute("rolepicked", "User");
		}
		// Hiển thị sản phẩm
		List<Game> games = gamedao.findTop3ByRating(5);
		m.addAttribute("top3game",games);
		
		return "trangchu";
	}
	@GetMapping("/game")
	public String game(
	        Model model,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(required = false) String keyword) {
		Users user = (Users) session.getAttribute("user");
		model.addAttribute("username",user);
		model.addAttribute("dscate",categorydao.findAll());
		Pageable pageable = PageRequest.of(page, 9);
		Page<Game> gamePage = gamedao.findAllGame(pageable);

		for(Game game : gamePage.getContent()) {

		    List<String> names = new ArrayList<>();

		    List<GameCategories> listCategories =
		            gamecategorydao.findByGameid(game.getGame_id());

		    for(GameCategories gc : listCategories) {
		        Categories c = categorydao.findById(gc.getCategory_id()).orElse(null);
		        if(c != null) {
		            names.add(c.getCategory_name());
		        }
		    }

//		    game.setCategories(String.join(" | ", names));
		}
	    Pageable pageable2 = PageRequest.of(page, size);

	    Page<Game> gamePage2;

	    if (keyword == null || keyword.isBlank()) {
	        gamePage2 = gamedao.findAll(pageable2);
	    } else {
	        gamePage2 = gamedao.findByGameNameContainingIgnoreCase(keyword, pageable2);
	    }

	    model.addAttribute("gamePage", gamePage2);
	    model.addAttribute("games", gamePage2.getContent());

	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", gamePage2.getTotalPages());

	    model.addAttribute("keyword", keyword);

	    return "user/product-list";
	}
	@RequestMapping("/user/logout")										//Đăng xuất
	public String logout() {
		session.removeAttribute("user");
		session.removeAttribute("rolepicked");
		return "redirect:/";
	}	
	@RequestMapping("/user/profile")									// Profile
	public String profile(Model m) {
		Users user = (Users) session.getAttribute("user");		
		if(user!=null) {
			m.addAttribute("rolepicked",session.getAttribute("rolepicked"));
			m.addAttribute("username",user.getUsername());
			m.addAttribute("user",user);
			List<Roles> list = userroledao.findByUsername(user.getUsername());
			m.addAttribute("dsrole",list);
			m.addAttribute("userEdit", user);
		}else{
			return "redirect:/login-register";
		}
		return "User/profile";
	}
	@PostMapping("/user/profile")
	public String editprofile(Model m,
			@RequestParam(value = "avatar", required = false) MultipartFile avatar,
			@RequestParam("fullname") String fullname,
			@RequestParam("email") String email) throws Exception {
		Users user = (Users) session.getAttribute("user");
		user.setFullname(fullname);
		user.setEmail(email);
		m.addAttribute("userEdit",user);
		if(avatar != null &&
				   !avatar.isEmpty()) {

				    if(user.getAvatar() != null){

				    	minioService.delete("images", user.getAvatar());
				    }
				    user.setAvatar(minioService.upload(avatar, "images"));
				}
		userdao.save(user);
		return "redirect:/user/profile";
	}
	@PostMapping("/user/role")
	public String role(Model m, @RequestParam("role") String role) {
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			m.addAttribute("username",user.getUsername());
			m.addAttribute("user",user);
			List<Roles> list = userroledao.findByUsername(user.getUsername());
			m.addAttribute("dsrole",list);
		}
		if(role.equals("R01")) {
			session.setAttribute("rolepicked", "Admin");
			return "forward:/admin/home";
		}else if(role.equals("R04")){
			session.setAttribute("rolepicked", "Staff");
			return "forward:/staff/home";
		}else if(role.equals("R03")){
			session.setAttribute("rolepicked", "Developer");	
			return "forward:/developer/home";
		}
		else if(role.equals("R02")){
			session.setAttribute("rolepicked", "User");	
			return "forward:/";
		}
		else {
			return "User/profile";
		}
	}
	@RequestMapping("/user/product")
	public String product() {
		return "User/product-list";
	}
}