package com.zero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zero.service.GameListService;

@Controller
@RequestMapping("/gameList")
public class GameListController {
	
	@Autowired
	private GameListService gameListService; 

}
