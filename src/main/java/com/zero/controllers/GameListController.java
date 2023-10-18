package com.zero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zero.service.GameListService;

@Controller
@RequestMapping("/gameList")
public class GameListController {
	
	@Autowired
	private GameListService gameListService; 
	
	@GetMapping("/list")
	public String list(Model model) {
		
		
		
		return "/gameList/list";
	}

}
