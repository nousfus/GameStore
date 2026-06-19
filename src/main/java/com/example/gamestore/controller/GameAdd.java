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

import com.example.gamestore.dao.CategoriesDao;
import com.example.gamestore.dao.GameCategoriesDao;
import com.example.gamestore.dao.GameDao;
import com.example.gamestore.dao.GameImagesDao;
import com.example.gamestore.dao.GameRequirementsDao;
import com.example.gamestore.dao.GameVersionsDao;
import com.example.gamestore.entity.Game;
import com.example.gamestore.entity.GameCategories;
import com.example.gamestore.entity.GameImages;
import com.example.gamestore.entity.GameRequirements;
import com.example.gamestore.entity.GameVersions;

@Controller
public class GameAdd {
	@Autowired
	GameDao gamedao;
	@Autowired
	GameVersionsDao gameversiondao;
	@Autowired
	GameRequirementsDao gamerequirementdao;
	@Autowired
	GameImagesDao gameimagedao;
	@Autowired
	GameCategoriesDao gamecategorydao;
	@Autowired
	CategoriesDao categorydao;
	
	@GetMapping("/game")
	public String listgame(Model m) {
		m.addAttribute("dsgame",gamedao.findAll());
		m.addAttribute("dsgameversion",gameversiondao.findAll());
		m.addAttribute("dsgamerequirement",gamerequirementdao.findAll());
		m.addAttribute("dsgameimage",gameimagedao.findAll());
		m.addAttribute("dsgamecategory",gamecategorydao.findAll());
		m.addAttribute("dscategory",categorydao.findAll());
		
		return "game/game";
	}
	@RequestMapping("/game/edit/{id}")
	public String edit(@PathVariable("id") String id, Model m) {
		Game game = gamedao.findById(id).orElse(null);
		m.addAttribute("gamedetail",game);
		  if(game.getVideo_url() == null) return "";

		    String id2 = "";

		    if(game.getVideo_url().contains("watch?v=")) {
		        id2 = game.getVideo_url().split("watch\\?v=")[1].split("&")[0];
		    }
		m.addAttribute("videourl","https://www.youtube.com/embed/"+id2);
		return "game/temp";
	}
	@PostMapping("/addgame")
	public String addGame(Model m,
			@RequestParam("gamename")String game_name,
			@RequestParam("description")String description,
			@RequestParam("price")float price,
			@RequestParam("category") String[] categories,
			@RequestParam String os,
	        @RequestParam String cpu,
	        @RequestParam String ram,
	        @RequestParam String gpu,
	        @RequestParam String directx,
	        @RequestParam String storage,
	        @RequestParam(value = "video", required = false) MultipartFile file,
	        @RequestParam("title")String title,
	        @RequestParam(value = "images", required = false) MultipartFile[] images,
	        @RequestParam(value = "thumbnail", required = false) MultipartFile[] thumbnail
	        ) throws IOException {
		String developer_id = "DEV001";										//Dev mẫu
		//Tạo thumbnail
		 //Images
		String fileNameThumbnail  = null;
        if (thumbnail == null || thumbnail.length == 0 ||
    	        (thumbnail.length == 1 && thumbnail[0].isEmpty())) {
    	        return "redirect:/game";
    	    }
    	    String temp_game_thumbnail = "GM001";
    	    // GameImages Id
    	    List<GameImages> list_thumbnail = gameimagedao.findAll();
    	    GameImages gameThumbnail = list_thumbnail.get(gameimagedao.findAll().size()-1);


    	    String uploadDirImagesThumbnail = "uploads/images/";

    	    // Tạo thư mục nếu chưa tồn tại
    	    Files.createDirectories(Paths.get(uploadDirImagesThumbnail));

    	    List<String> imagePathsThumbnail = new ArrayList<>();

    	    for (MultipartFile fileThumbnail : images) {

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

    	        imagePathsThumbnail.add("/images/" + fileNameThumbnail);
    			int lastidimages = Integer.parseInt(gameThumbnail.getImage_id().substring(5));
    			String new_game_images_id = "IMG00" + (lastidimages +1);
    	        gameimagedao.save(new GameImages(new_game_images_id,temp_game_thumbnail,fileNameThumbnail));
    	    }
    	    
		// Tạo game id
		List<Game> list = gamedao.findAll();
		Game abc = list.get(gamedao.findAll().size()-1);
		int lastID = Integer.parseInt(abc.getGame_id().substring(4));
		String new_game_id = "GM00"+(lastID+1);								// Tạo game id mới
		Date date = new Date(System.currentTimeMillis());
		

		
		//Thêm Category
		for(String category : categories){
	        gamecategorydao.save(
	            new GameCategories(new_game_id, category)
	        );
	    }
		
		//Phiên Bản game
		List<GameVersions> gamever = gameversiondao.findAll();
		GameVersions gameversion = gamever.get(gameversiondao.findAll().size()-1);
		int lastversionid = Integer.parseInt(gameversion.getVersion_id().substring(5));
		String new_version_id = "VER00"+(lastversionid+1);
		gameversiondao.save(new GameVersions(new_version_id,new_game_id,"1.0.0","Phiên bản đầu tiên",date));
		
		
		//Cấu hình game
		List<GameRequirements> gamere = gamerequirementdao.findAll();
		GameRequirements gr = gamere.get(gamerequirementdao.findAll().size()-1);
		int lastrequiremntid = Integer.parseInt(gr.getRequirement_id().substring(5));
		String new_requirement_id = "REQ00"+(lastrequiremntid+1);
		gamerequirementdao.save(new GameRequirements(new_requirement_id,new_game_id,"Minimum",os,cpu,ram,gpu,directx,storage));
		
		//Video 
		if (file == null || file.isEmpty()) {
	        return "redirect:/game";
	    }

		
        String fileName = file.getOriginalFilename();

        String uploadDir = "src/main/resources/static/videos/";

        Path path = Paths.get(uploadDir + fileName);

        Files.copy(file.getInputStream(),
                   path,
                   StandardCopyOption.REPLACE_EXISTING);

        m.addAttribute("videoPath",
                           "/videos/" + fileName);
        
		gamedao.save(new Game(new_game_id,developer_id,game_name,description,price,date,0,fileNameThumbnail,"Active",fileName));

        //Images
        if (images == null || images.length == 0 ||
    	        (images.length == 1 && images[0].isEmpty())) {
    	        return "redirect:/game";
    	    }
    	    String temp_game_image = "GM001";
    	    // GameImages Id
    	    List<GameImages> list_images = gameimagedao.findAll();
    	    GameImages gameimg = list_images.get(gameimagedao.findAll().size()-1);


    	    String uploadDirImages = "uploads/images/";

    	    // Tạo thư mục nếu chưa tồn tại
    	    Files.createDirectories(Paths.get(uploadDirImages));

    	    List<String> imagePaths = new ArrayList<>();

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

    	        imagePaths.add("/images/" + fileNameImages);
    			int lastidimages = Integer.parseInt(gameimg.getImage_id().substring(5));
    			String new_game_images_id = "IMG00" + (lastidimages +1);
    	        gameimagedao.save(new GameImages(new_game_images_id,temp_game_image,fileNameImages));
    	    }

    	    m.addAttribute("imagePaths", imagePaths);
		return "game/game";
	}
}
