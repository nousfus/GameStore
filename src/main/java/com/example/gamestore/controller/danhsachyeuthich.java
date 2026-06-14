package com.example.gamestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class danhsachyeuthich {
	@RequestMapping("/user/wishlist")
	public String wishlist (){
	return "User/wishlist";
   }
}
