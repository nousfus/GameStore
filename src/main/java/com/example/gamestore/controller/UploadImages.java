package com.example.gamestore.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadImages {
	@GetMapping("/upload-images")
	public String abc() {
		return "formImages";
	}
	@PostMapping("/upload-images")
	public String uploadImages(
	        @RequestParam("images") MultipartFile[] images,
	        Model model) throws Exception {

	    String uploadDir = "uploads/images/";

	    // Tạo thư mục nếu chưa tồn tại
	    Files.createDirectories(Paths.get(uploadDir));

	    List<String> imagePaths = new ArrayList<>();

	    for (MultipartFile file : images) {

	        if (file.isEmpty()) {
	            continue;
	        }

	        String fileName = System.currentTimeMillis()
	                + "_" + file.getOriginalFilename()
	                        .replaceAll("\\s+", "_");

	        Path path = Paths.get(uploadDir, fileName);

	        Files.copy(
	                file.getInputStream(),
	                path,
	                StandardCopyOption.REPLACE_EXISTING);

	        imagePaths.add("/images/" + fileName);
	    }

	    model.addAttribute("imagePaths", imagePaths);

	    return "ShowImages";
	}
}
