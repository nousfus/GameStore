package com.example.gamestore.controller;


import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gamestore.dao.*;
import com.example.gamestore.entity.Cart;
import com.example.gamestore.entity.CartItems;
import com.example.gamestore.entity.Categories;
import com.example.gamestore.entity.Discounts;
import com.example.gamestore.entity.Game;
import com.example.gamestore.entity.GameCategories;
import com.example.gamestore.entity.GameImages;
import com.example.gamestore.entity.OrderDetails;
import com.example.gamestore.entity.Orders;
import com.example.gamestore.entity.Payments;
import com.example.gamestore.entity.Reviews;
import com.example.gamestore.entity.Users;
import com.example.gamestore.service.GameSpecification;
import com.example.gamestore.service.MinioService;
import com.google.common.net.HttpHeaders;

import io.minio.MinioClient;
import jakarta.servlet.http.HttpSession;

@Controller
public class GameList {

	@Autowired
	HttpSession session;
	@Autowired
	CategoriesDao categorydao;
	@Autowired
	GameDao gamedao;
	@Autowired
	CartDao cartDao;
	@Autowired
	CartItemsDao cartitemdao;
	@Autowired
	OrdersDao orderdao;
	@Autowired
	OrderDetailsDao orderdetaildao;
	@Autowired
	DiscountsDao discountdao;
	@Autowired
	PaymentsDao paymentdao;
	@Autowired
	ReviewsDao reviewsdao;
	@Autowired
	GameCategoriesDao gamecategorydao;
	@Autowired
	GameImagesDao gameimagedao;
	@Autowired
	MinioService minioService;
	@GetMapping("/user/product-list")
	public String productList(
	        @RequestParam(defaultValue = "0") int page,
	        Model m) {
		Users user = (Users) session.getAttribute("user");
		m.addAttribute("username",user);
		m.addAttribute("dscate",categorydao.findAll());
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

		   m.addAttribute("categories",names);
		}
		if(user!=null) {
			List<String> gamePaidId = new ArrayList<>();
			for(OrderDetails d : orderdetaildao.findAll()) {
				if(d.getOrder().getUsername().equals(user.getUsername()) && d.getOrder().getStatus().equals("Paid")) {
					gamePaidId.add(d.getGame_id());
				}
			}
			m.addAttribute("gamePaidId",gamePaidId);
			Cart cart = cartDao.findByUsername(user.getUsername());
			List<CartItems> cartitemlist = cartitemdao.findByCartId(cart.getCart_id());
			List<String> listgame = new ArrayList<>();
			for(CartItems c : cartitemlist) {
				listgame.add(c.getGame().getGame_id());
			}
			m.addAttribute("listgame",listgame);
		}
		
	    m.addAttribute("gamePage", gamePage);
	    m.addAttribute("currentPage", page);
		m.addAttribute("OrderDetail",orderdetaildao);

	    return "user/product-list";
	}
	@GetMapping("/user/product-list/filter")
	public String filter(
	        @RequestParam(required = false) List<String> categories,
	        @RequestParam(required = false) String ram,
	        @RequestParam(required = false) String storage,
	        @RequestParam(required = false) Double price,
	        @RequestParam(defaultValue = "0") int page,
	        Model m) {
		Users user = (Users) session.getAttribute("user");
		m.addAttribute("username",user);
	    Specification<Game> spec = Specification
	            .where(GameSpecification.hasRam(ram))
	            .and(GameSpecification.hasStorage(storage))
	            .and(GameSpecification.priceLessThan(price))
	            .and(GameSpecification.hasCategory(categories));

	    Pageable pageable = PageRequest.of(page, 9);
	    Page<Game> gamePage = gamedao.findAll(spec, pageable);
	    
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
		    m.addAttribute("categories",names);
		}
	    
	    m.addAttribute("gamePage", gamePage);
	    m.addAttribute("currentPage", page);
	    m.addAttribute("dscate",categorydao.findAll());
	    
	    return "user/product-list";
	}
	@RequestMapping("/user/product-detail/{id}")								// Trang chi tiết sản phẩm
	public String detail(@PathVariable("id") String id,Model m) throws Exception {
		//Hiển thị thông tin game
		Game game = gamedao.findById(id).orElse(null);
		m.addAttribute("game",game);
		m.addAttribute("gameStatus",game.getStatus());
			
		//discount
		Discounts discount = discountdao.findByGameId(id);
		m.addAttribute("gameDiscount",discount);
		if(discount!=null) {
			m.addAttribute("discount",discount.getdiscount_percent());
			float price = game.getPrice() - ((game.getPrice()*discount.getdiscount_percent()) / 100);
			m.addAttribute("priceafterdiscount",price);	
		}

		// Đánh giá
		List<Reviews> reviews = reviewsdao.findByGameid(id);
		m.addAttribute("countreviews",reviews.size());
		double star = reviews.stream()
		        .mapToInt(Reviews::getRating)
		        .average()
		        .orElse(0.0);

		m.addAttribute("stars", star);
		m.addAttribute("reviews",reviews);
		
		//Hiển thị thể loại game
		List<String> list = new ArrayList<>();
		List<GameCategories> gameCategories = gamecategorydao.findByGameid(id);
		for(GameCategories g : gameCategories) {
			for(Categories c : categorydao.findAll()) {
				if(g.getCategory_id().equals(c.getCategory_id())) {
					list.add(c.getCategory_name());
				}
			}	
		}
		m.addAttribute("listCategories",list);
		
		// Hiển thị hình ảnh game
		List<GameImages> listimages = gameimagedao.findByGameid(id);
		m.addAttribute("listimages",listimages);
		
		//Hiển thị video game
		  if(game.getVideo_url() == null) return "";
		    if (game.getVideo_url().contains("watch?v=")) {

		        String VideoId = game.getVideo_url()
		                .split("watch\\?v=")[1]
		                .split("&")[0];

		        m.addAttribute("youtube", true);
		        m.addAttribute("videourl",
		                "https://www.youtube.com/embed/" + VideoId);

		    }else {

		        m.addAttribute("youtube", false);
		        m.addAttribute("videourl",
		                "/videos/" + game.getVideo_url());

		    }

	    
	    session.setAttribute("game",game);
		return "User/product-detail";
	}
	@PostMapping("/user/addComment")
	public String addcommen(@RequestParam("gameId") String gameId,
							@RequestParam("rating") String rating,
							@RequestParam("comment") String comment) {
		Users user = (Users) session.getAttribute("user");
		if(user==null) {
			return "redirect:/login-register";
		}
		if(rating!=null) {
			List<Reviews> list = reviewsdao.findAll();
			Reviews reviews = list.get(reviewsdao.findAll().size()-1);
			String reviews_id = "RV00" + ((Integer.parseInt(reviews.getReview_id().substring(4)))+1); 
			Date date = new Date(System.currentTimeMillis());
			reviewsdao.save(new Reviews(reviews_id,user.getUsername(),gameId,Integer.parseInt(rating),comment,date));
		}else {
			List<Reviews> list = reviewsdao.findAll();
			Reviews reviews = list.get(reviewsdao.findAll().size()-1);
			String reviews_id = "RV00" + ((Integer.parseInt(reviews.getReview_id().substring(4)))+1); 
			Date date = new Date(System.currentTimeMillis());
			reviewsdao.save(new Reviews(reviews_id,user.getUsername(),gameId,1,comment,date));
		}
		
		return "forward:/user/product-detail/"+gameId;
	}
	@PostMapping("/user/addcart/{id}")
	public String addcart(@PathVariable("id") String gameid) {
		Users user = (Users) session.getAttribute("user");
		if(user==null) {
			return "redirect:/login-register";
		}
		Cart cart = cartDao.findByUsername(user.getUsername());
	    Game game = gamedao.findById(gameid).orElse(null);


	        String newId;

	        List<CartItems> list = cartitemdao.findByCartId(cart.getCart_id());

	        if (!list.isEmpty()) {

	            CartItems last = list.get(list.size() - 1);

	            int num = Integer.parseInt(
	                    last.getCart_item_id().substring(4));

	            newId = "CI00" + (num + 1);

	        } else {

	            newId = "CI001";
	        }
	        
	        CartItems newItem = new CartItems(newId,cart.getCart_id(),game);
	        cartitemdao.save(newItem);

	    return "redirect:/user/product-list";
	}
	@PostMapping("/delete/{abc}")
	public String dleete(@PathVariable("abc")String id) {
		CartItems cartitem = cartitemdao.findById(id).orElse(null);
		String cartid = cartitem.getCartid();
		cartitemdao.delete(cartitem);
		return "redirect:/user/cartitem/"+cartid;
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
		Users user = (Users) session.getAttribute("user");
		String tempusername = user.getUsername(); 
		int total = 0;														//Tổng giá sản phẩm
		Game game = new Game();
		for(CartItems c : cartitemdao.findAll()) {
			if(c.getCartid().equals(neededCart_id)) {
				game = gamedao.findById(c.getGame().getGame_id()).orElse(null);
				total += game.getPrice();
			}
		}
		Orders order = new Orders(neworderid,tempusername,date,total,"Pending");orderdao.save(order);	//tạo order
		
		List<OrderDetails> temp = orderdetaildao.findAll();
		OrderDetails od = temp.get(orderdetaildao.findAll().size()-1);
		int lastorder = Integer.parseInt(od.getOrder_detail_id().substring(5));
		String neworderdetailid = "ODD00"+(lastorder+1);									//Mã orderdetails
		
		Discounts discount = discountdao.findByGameId(game.getGame_id());					// Discount của game
		float discountamount = 0;
		for(CartItems c : cartitemdao.findByCartId(neededCart_id)) {
			if(discount!=null) {
				discountamount = (c.getGame().getPrice() * discount.getdiscount_percent()) / 100;
			}else {discountamount = 0;}
			OrderDetails odd = new OrderDetails(neworderdetailid,order,c.getGame().getGame_id(),c.getGame().getPrice(),discountamount);orderdetaildao.save(odd);	
		}
		
		//Payments
		List<Payments> hahaha = paymentdao.findAll();
		Payments pa = hahaha.get(paymentdao.findAll().size()-1);
		
		int payment = Integer.parseInt(pa.getPayment_id().substring(4));
		String newpaymentid = "PM00"+(payment+1);									//Mã payment id
		
		int lsattrancation = Integer.parseInt(pa.getTransactionCode().substring(7));
		String newtransactionid = "TRANS00"+(lsattrancation+1);									//Mã transaction
		
		Payments payments = new Payments(newpaymentid,neworderid,pay,"Success",date,newtransactionid);paymentdao.save(payments);
		
		for(CartItems c : cartitemdao.findAll()) {
			if(c.getCartid().equals("neededCart_id")) {
				cartitemdao.delete(c);
			}
		}
		return "forward:/user/product-list";
	}
	@GetMapping("/images/{fileName}")
	public ResponseEntity<InputStreamResource> image(
	        @PathVariable String fileName) throws Exception {

	    InputStream inputStream =
	            minioService.getFile("images", fileName);

	    return ResponseEntity.ok()
	            .contentType(MediaType.IMAGE_JPEG)
	            .body(new InputStreamResource(inputStream));

	}
	@GetMapping("/videos/{fileName:.+}")
	public ResponseEntity<InputStreamResource> video(
	        @PathVariable String fileName) throws Exception {
	    InputStream inputStream = minioService.getFile("videos", fileName);

	    return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType("video/mp4"))
	            .body(new InputStreamResource(inputStream));
	}
	@GetMapping("/games/{fileName}")
	public ResponseEntity<InputStreamResource> download(
	        @PathVariable String fileName) throws Exception {

	    InputStream inputStream =
	            minioService.getFile("games", fileName);

	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION,
	                    "attachment; filename=\"" + fileName + "\"")
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .body(new InputStreamResource(inputStream));

	}
}
//<a th:href="@{/games/{file}(file=${game.gameFile})}">
//Download
//</a>
