package com.example.gamestore.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.gamestore.dao.GameCategoriesDao;
import com.example.gamestore.dao.GameDao;
import com.example.gamestore.dao.GameImagesDao;
import com.example.gamestore.dao.GameRequirementsDao;
import com.example.gamestore.dao.GameVersionsDao;
import com.example.gamestore.dao.GameVideosDao;

@Controller
public class GameAdd {
	@Autowired
	GameDao gamedao;
	@Autowired
	GameVersionsDao gameversiondao;
	@Autowired
	GameVideosDao gamevideodao;
	@Autowired
	GameRequirementsDao gamerequirementdao;
	@Autowired
	GameImagesDao gameimagedao;
	@Autowired
	GameCategoriesDao gamecategorydao;
	
	@RequestMapping("/game")
	public String listgame(Model m) {
		m.addAttribute("dsgame",gamedao.findAll());
		m.addAttribute("dsgameversion",gameversiondao.findAll());
		m.addAttribute("dsgamevideo",gamevideodao.findAll());
		m.addAttribute("dsgamerequirement",gamerequirementdao.findAll());
		m.addAttribute("dsgameimage",gameimagedao.findAll());
		m.addAttribute("dsgamecategory",gamecategorydao.findAll());
		return "game/game";
	}
	@GetMapping("/upload-video")
	public String showForm() {
	    return "upload";
	}
	@PostMapping("/upload-video")	
    public String uploadVideo(
            @RequestParam("video") MultipartFile file,
            Model model) throws Exception {

        String fileName = file.getOriginalFilename();

        String uploadDir = "src/main/resources/static/videos/";

        Path path = Paths.get(uploadDir + fileName);

        Files.copy(file.getInputStream(),
                   path,
                   StandardCopyOption.REPLACE_EXISTING);

        model.addAttribute("videoPath",
                           "/videos/" + fileName);

        return "video";
    }
}
