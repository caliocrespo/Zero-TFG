package com.zero.controllers;


import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zero.service.GameService;
import com.zero.service.GenreService;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/game")
public class GameController {
	
	@Autowired
    private GameService gameService;


    //@PostConstruct
    public void getAllGame() throws ParseException {
        gameService.getAPIGames();
    }
}






