package com.example.gamestore.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.gamestore.dao.*;
import com.example.gamestore.entity.DeveloperProfiles;
import com.example.gamestore.entity.Game;
import com.example.gamestore.entity.GameCategories;
import com.example.gamestore.entity.GameImages;
import com.example.gamestore.entity.OrderDetails;
import com.example.gamestore.entity.Roles;
import com.example.gamestore.entity.Users;
import com.example.gamestore.service.MinioService;
import io.minio.MinioClient;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/developer")
public class TrangChuDeveloper {
	@Autowired
	GameDao gamedao;
	@Autowired
	HttpSession session;
	@Autowired
	DeveloperProfilesDao developerdao;
	@Autowired
	CategoriesDao categorydao;
	@Autowired
	UserRolesDao userroledao;
	@Autowired
	RolesDao roledao;
	@Autowired
	GameCategoriesDao gamecategorydao;
	@Autowired
	GameImagesDao gameimagedao;
	@Autowired
	OrdersDao orderdao;
	@Autowired
	OrderDetailsDao orderdetaildao;
	@Autowired
	private MinioClient minioClient;
	@Autowired
	MinioService minioService;
	@RequestMapping("/home")
	public String home(Model m) {
		Users user = (Users) session.getAttribute("user");
		if(user!=null) {
			m.addAttribute("username",user.getUsername());
		}
		return "Developer/home";
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
	@RequestMapping("/game-management")
	public String game_mana(Model m) {
		Users user = (Users) session.getAttribute("user");
		DeveloperProfiles dev = developerdao.findByUsername(user.getUsername());
		if(dev!=null) {
			List<Game> listgame = gamedao.findByDeveloper_Developerid(dev.getDeveloperid());
			
			m.addAttribute("listgame",listgame);
			m.addAttribute("listcate",categorydao.findAll());
			m.addAttribute("gameEdit",new Game());
			
		}
		return "Developer/game-management";
	}
	@GetMapping("/game-management/edit/{id}")
	public String editGame(@PathVariable("id")String gameid, Model m) {	
		Users user = (Users) session.getAttribute("user");
		DeveloperProfiles dev = developerdao.findByUsername(user.getUsername());
		if(dev!=null) {
			List<Game> listgame = gamedao.findByDeveloper_Developerid(dev.getDeveloperid());
			m.addAttribute("listgame",listgame);
			m.addAttribute("listcate",categorydao.findAll());
		}
		
		Game game = gamedao.findById(gameid).orElse(null);
		m.addAttribute("gameEdit",gamedao.findById(gameid));
		List<String> selectedCategories =
	            gamecategorydao.findByGameid(gameid)
	                           .stream()
	                           .map(gc -> gc.getCategory_id())
	                           .toList();
		
		List<GameImages> images = gameimagedao.findByGameid(gameid);
		m.addAttribute("gameImages", images);	
		
		m.addAttribute("selectedCategories", selectedCategories);
		m.addAttribute("gameEdit",game);
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

		return "Developer/game-management";
	}
	@PostMapping("/create-game")
	public String addgame(Model m,
			@RequestParam("GameId") String GameId,
			@RequestParam("gamename")String gamename,
			@RequestParam("description")String description,
			@RequestParam("price")float price,
			@RequestParam(required = false , name ="categories") String[] categories,
	        @RequestParam ("ram")String ram,
	        @RequestParam ("storage")String storage,
	        @RequestParam(value = "video", required = false) MultipartFile file,
	        @RequestParam(required = false, name = "url")String url,
	        @RequestParam(value = "images", required = false) MultipartFile[] images,
	        @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
	        @RequestParam(value="gameFile", required=false) MultipartFile gameFile) throws Exception {
		
		Users user = (Users) session.getAttribute("user");
		DeveloperProfiles dev = developerdao.findByUsername(user.getUsername());
		m.addAttribute("gameEdit",new Game());

   		// Tạo game id
   		List<Game> list = gamedao.findAll();
   		Game abc = list.get(gamedao.findAll().size()-1);
   		int lastID = Integer.parseInt(abc.getGame_id().substring(4));
   		Date date = new Date(System.currentTimeMillis());
		String newGameId = GameId==null || GameId.trim().isEmpty()  ? "GM00"+(lastID+1) : GameId;
		
		boolean isEdit = gamedao.existsById(GameId);
		Game game;
		if(isEdit){
		    game = gamedao.findById(GameId).orElse(null);
		}else{
		    game = new Game();
		    game.setGame_id(newGameId);
		    game.setDeveloper(dev);
		    game.setRelease_date(date);
		    game.setRating(0);
		    game.setStatus("Unactive");
		}
		
		//Tạo thumbnail
		if(thumbnail != null && !thumbnail.isEmpty()) {

			if (isEdit && game.getThumbnail() != null && !game.getThumbnail().isBlank()) {
				minioService.delete("images", game.getThumbnail());
			}
			game.setThumbnail(minioService.upload(thumbnail, "images"));
		}
 
    		
    		//Video 
		if(file != null && !file.isEmpty()) {

		    if(isEdit &&
		       game.getVideo_url() != null &&
		       !game.getVideo_url().startsWith("http") && !game.getVideo_url().isBlank()) {

		    	minioService.delete("videos", game.getVideo_url());
		    }

		    game.setVideo_url(minioService.upload(file, "videos"));
		}
		else if(url != null && !url.isBlank()) {

		    game.setVideo_url(url);
		}
		
		//FIle game
		// Upload Game File
		if (gameFile != null && !gameFile.isEmpty()) {

		    // Nếu edit thì xóa file cũ
		    if (isEdit &&
		        game.getFilegame() != null &&
		        !game.getFilegame().isBlank()) {
		    	minioService.delete("games", game.getFilegame());
		    }
		    game.setFilegame(minioService.upload(gameFile, "games"));
		}
		game.setGameName(gamename);
		game.setDescription(description);
		game.setPrice(price);
		game.setRam(ram);
		game.setStorage(storage);
		gamedao.save(game);
		//Images
		if (images != null &&
			    images.length > 0 &&
			    !images[0].isEmpty()) {

			    // Xóa ảnh cũ nếu đang edit
			    if(isEdit){

			        List<GameImages> oldImages =
			                gameimagedao.findByGameid(newGameId);

			        for(GameImages img : oldImages){

			        	minioService.delete("images", img.getImage_url());
			        }

			        gameimagedao.deleteAll(oldImages);
			    }

			    // Lấy id cuối
			    List<GameImages> listImages = gameimagedao.findAll();

			    int lastidimages = 0;

			    if(!listImages.isEmpty()){
			        GameImages lastImg =
			                listImages.get(listImages.size()-1);

			        lastidimages =
			                Integer.parseInt(
			                        lastImg.getImage_id()
			                               .substring(5));
			    }

			    for (MultipartFile file2 : images) {

			        if (file2.isEmpty()) {
			            continue;
			        }

			        GameImages gi = new  GameImages();

			        lastidimages++;

			        String newImageId =
			                "IMG00" + lastidimages;
			        gi.setImage_url(minioService.upload(file2, "images"));
			        gi.setGame_id(newGameId);
			        gi.setImage_id(newImageId);
			        gameimagedao.save(gi);
			    }
			}
		//Thêm Category
		if(isEdit){
		    gamecategorydao.deleteByGameid(game.getGame_id());
		}
		if(categories != null){
		    for(String category : categories){
		        gamecategorydao.save(
		            new GameCategories(
		                game.getGame_id(),
		                category
		            )
		        );
		    }
		}
            
		return "forward:/developer/game-management";
	}
	@RequestMapping("/revenue-tracking")
	public String revenue(
	        Model m,
	        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
	        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

		if(startDate == null){
		    startDate = LocalDate.now().minusDays(14);
		}

		if(endDate == null){
		    endDate = LocalDate.now();
		}

		Users user = (Users) session.getAttribute("user");
		DeveloperProfiles dev =
		        developerdao.findByUsername(user.getUsername());

		List<Object[]> data =
		        orderdetaildao.revenueByDate(
		                dev.getDeveloperid(),
		                startDate,
		                endDate);

		Map<LocalDate, Double> revenueMap = new HashMap<>();
		
		for (Object[] row : data) {
		    LocalDate date = (LocalDate) row[0];
		    Double revenue = ((Number) row[1]).doubleValue();

		    revenueMap.put(date, revenue);
		}
		
		List<LocalDate> dates = new ArrayList<>();
		List<Double> revenues = new ArrayList<>();
	
		for(LocalDate d = startDate;
		    !d.isAfter(endDate);
		    d = d.plusDays(1)){

		    dates.add(d);
		    revenues.add(revenueMap.getOrDefault(d,0.0));
		}
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
		m.addAttribute("dates",dates);
		m.addAttribute("revenues",revenues);
		m.addAttribute("startDate",startDate);
		m.addAttribute("endDate",endDate);
		
		List<List<Object>> revenue = new ArrayList<>();
		int luotMua = 0;
		double tongDoanhThu = 0;
		double doangThuThangNay =0;
		for(Game g : gamedao.findByDeveloper_Developerid(dev.getDeveloperid())) {
			List<Object> abc = new ArrayList<>();
			double total = 0;
			List<OrderDetails> list = orderdetaildao.findByGameId(g.getGame_id());
			for(OrderDetails d : list) {
				total += (d.getOrder().getTotal_amount() * 70 ) / 100; // Lấy 70% tổng sản phẩm của game
				tongDoanhThu += total;
				luotMua++;
				Date date = new Date(System.currentTimeMillis());
				if(d.getOrder().getOrder_date().getMonth() == date.getMonth()) {
					doangThuThangNay += total;		
				}
			abc.add(g.getGameName());
			abc.add(total);
			abc.add(list.size());
			revenue.add(abc);
			}
		}

		m.addAttribute("revenue",revenue);
		m.addAttribute("luotMua",luotMua);
		m.addAttribute("tongDoanhThu",tongDoanhThu);
		m.addAttribute("doangThuThangNay",doangThuThangNay);


		return "Developer/revenue-tracking";
	}
	@RequestMapping("/review-feedback")
	public String reviews(Model m) {
		
		return "Developer/review-feedback";
	}
	
}