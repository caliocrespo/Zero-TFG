package com.zero.controllers;


import java.text.ParseException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zero.domain.Game;
import com.zero.service.GameService;
import com.zero.service.GenreService;

import jakarta.annotation.PostConstruct;

@Controller
public class GameController {
	
	@Autowired
    private GameService gameService;


    //@PostConstruct
    public void getAllGame() throws ParseException {
        gameService.getAPIGames();
    }
    
    @GetMapping("/games/list")
    public ModelAndView list(Model model) {
    	
    	
    	Collection<Game> games;
    	
    	games = null;
    	
    	ModelAndView mav = new ModelAndView("/games/list");
		return mav;
    }
    
    
}






