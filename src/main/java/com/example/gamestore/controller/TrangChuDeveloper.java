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
	        @RequestParam String ram,
	        @RequestParam String storage,
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
		
		//Tạo thumbnail
		String fileNameThumbnail  = null;
        if (thumbnail == null || thumbnail.length == 0 ||
    	        (thumbnail.length == 1 && thumbnail[0].isEmpty())) {
    	        return "redirect:/developer/game-management";
    	    }
    	    // GameImages Id
    	    List<GameImages> list_thumbnail = gameimagedao.findAll();
    	    GameImages gameThumbnail = list_thumbnail.get(gameimagedao.findAll().size()-1);


    	    String uploadDirImagesThumbnail = "uploads/images/";

    	    // Tạo thư mục nếu chưa tồn tại
    	    Files.createDirectories(Paths.get(uploadDirImagesThumbnail));

    	    List<String> imagePathsThumbnail = new ArrayList<>();

    	    for (MultipartFile fileThumbnail : thumbnail) {

    	        if (fileThumbnail.isEmpty()) {
    	            continue;
    	        }

    	        fileNameThumbnail = System.currentTimeMillis()
    	                + "_" + fileThumbnail.getOriginalFilename()
    	                        .replaceAll("\\s+", "_");

    	        Path pathThumbnail = Paths.get(uploadDirImagesThumbnail, fileNameThumbnail);

    	        Files.copy(
    	        		fileThumbnail.getInputStream(),
    	        		pathThumbnail,
    	                StandardCopyOption.REPLACE_EXISTING);

    	        imagePathsThumbnail.add("/uploads/images/" + fileNameThumbnail);
    	    }
 
    		
    		//Video 
    	    String fileName = null;
    		if (file == null || file.isEmpty()) {
    	       fileName = url;

    	    }else {
    	    	  fileName = file.getOriginalFilename();

    	            String uploadDir = "uploads/videos/";

    	            Path path = Paths.get(uploadDir + fileName);

    	            Files.copy(file.getInputStream(),
    	                       path,
    	                       StandardCopyOption.REPLACE_EXISTING);

    	            m.addAttribute("videoPath",
    	                               "/videos/" + fileName);
    	    }
            gamedao.save(new Game(newGameId,dev,gamename,description,price,date,0,fileNameThumbnail,"Unactive",fileName,ram,storage));
    	   	  //Thêm Category
	    		for(String category : categories){
	    	        gamecategorydao.save(
	    	            new GameCategories(newGameId, category)
	    	        );
	    	    }
            //Images
            if (images == null || images.length == 0 ||
        	        (images.length == 1 && images[0].isEmpty())) {
    	        return "redirect:/developer/game-management";
        	    }
        	    // GameImages Id
        	    List<GameImages> list_images = gameimagedao.findAll();
        	    GameImages gameimg = list_images.get(gameimagedao.findAll().size()-1);


        	    String uploadDirImages = "uploads/images/";

        	    // Tạo thư mục nếu chưa tồn tại
        	    Files.createDirectories(Paths.get(uploadDirImages));

        	    List<String> imagePaths = new ArrayList<>();
    			int lastidimages = Integer.parseInt(gameimg.getImage_id().substring(5));
        	    for (MultipartFile file2 : images) {

        	        if (file2.isEmpty()) {
        	            continue;
        	        }

        	        String fileNameImages = System.currentTimeMillis()
        	                + "_" + file2.getOriginalFilename()
        	                        .replaceAll("\\s+", "_");

        	        Path path2 = Paths.get(uploadDirImages, fileNameImages);

        	        Files.copy(
        	                file2.getInputStream(),
        	                path2,
        	                StandardCopyOption.REPLACE_EXISTING);

        	        imagePaths.add("/uploads/images/" + fileNameImages);
        	        lastidimages++;
        			String new_game_images_id = "IMG00" +lastidimages;
        	        gameimagedao.save(new GameImages(new_game_images_id,newGameId,fileNameImages));
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


// storage hiển thị số lượng và select phần (MB/GB)