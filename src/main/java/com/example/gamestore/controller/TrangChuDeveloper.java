package com.example.gamestore.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.gamestore.entity.Roles;
import com.example.gamestore.entity.Users;

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
	        @RequestParam(value = "thumbnail", required = false) MultipartFile[] thumbnail) throws IOException {
		
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
		if(thumbnail != null &&
				   thumbnail.length > 0 &&
				   !thumbnail[0].isEmpty()) {

				    if(isEdit && game.getThumbnail() != null){

				        Path oldThumb = Paths.get(
				                "uploads/images/",
				                game.getThumbnail());

				        Files.deleteIfExists(oldThumb);
				    }

				    MultipartFile thumb = thumbnail[0];

				    String thumbnailName =
				            System.currentTimeMillis()
				            + "_"
				            + thumb.getOriginalFilename()
				                  .replaceAll("\\s+","_");

				    Path thumbPath =
				            Paths.get("uploads/images/",
				                      thumbnailName);

				    Files.copy(
				            thumb.getInputStream(),
				            thumbPath,
				            StandardCopyOption.REPLACE_EXISTING);

				    game.setThumbnail(thumbnailName);
				}
 
    		
    		//Video 
		if(file != null && !file.isEmpty()) {

		    if(isEdit &&
		       game.getVideo_url() != null &&
		       !game.getVideo_url().startsWith("http")) {

		        Path oldVideo =
		                Paths.get(
		                "uploads/videos/",
		                game.getVideo_url());

		        Files.deleteIfExists(oldVideo);
		    }

		    String videoName =
		            System.currentTimeMillis()
		            + "_"
		            + file.getOriginalFilename();

		    Path videoPath =
		            Paths.get("uploads/videos/",
		                      videoName);

		    Files.copy(
		            file.getInputStream(),
		            videoPath,
		            StandardCopyOption.REPLACE_EXISTING);

		    game.setVideo_url(videoName);
		}
		else if(url != null && !url.isBlank()) {

		    game.setVideo_url(url);
		}
		
		//Images
		if (images != null &&
			    images.length > 0 &&
			    !images[0].isEmpty()) {

			    // Xóa ảnh cũ nếu đang edit
			    if(isEdit){

			        List<GameImages> oldImages =
			                gameimagedao.findByGameid(newGameId);

			        for(GameImages img : oldImages){

			            Path oldPath = Paths.get(
			                    "uploads/images/",
			                    img.getImage_url());

			            Files.deleteIfExists(oldPath);
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

			    String uploadDirImages = "uploads/images/";

			    Files.createDirectories(
			            Paths.get(uploadDirImages));

			    for (MultipartFile file2 : images) {

			        if (file2.isEmpty()) {
			            continue;
			        }

			        String fileNameImages =
			                System.currentTimeMillis()
			                + "_"
			                + file2.getOriginalFilename()
			                       .replaceAll("\\s+", "_");

			        Path path2 =
			                Paths.get(
			                        uploadDirImages,
			                        fileNameImages);

			        Files.copy(
			                file2.getInputStream(),
			                path2,
			                StandardCopyOption.REPLACE_EXISTING);

			        lastidimages++;

			        String newImageId =
			                "IMG00" + lastidimages;

			        gameimagedao.save(
			            new GameImages(
			                newImageId,
			                newGameId,
			                fileNameImages
			            )
			        );
			    }
			}
		game.setGameName(gamename);
		game.setDescription(description);
		game.setPrice(price);
		game.setRam(ram);
		game.setStorage(storage);
		gamedao.save(game);
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
	public String revenue(Model m) {
		
		return "Developer/revenue-tracking";
	}
	@RequestMapping("/review-feedback")
	public String reviews(Model m) {
		
		return "Developer/review-feedback";
	}
}

//
//Ngoài ra bạn đang dùng:
//
//substring(5)
//
//với ID dạng:
//
//IMG001
//
//thì:
//
//"IMG001".substring(5)
//
//sẽ trả về "1".
//
//Nhưng nếu sau này có:
//
//IMG010
//IMG100
//
//thì dễ lỗi format. An toàn hơn nên dùng:
//
//Integer.parseInt(
//    lastImg.getImage_id()
//           .replace("IMG", "")
//);
//
//hoặc
//
//Integer.parseInt(
//    lastImg.getImage_id()
//           .substring(3)
//);
//
//vì prefix "IMG" luôn dài 3 ký tự. Điều này ổn định hơn substring(5).