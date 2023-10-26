package com.zero.controllers;


import java.text.ParseException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;


import com.zero.domain.Game;
import com.zero.service.GameService;
import com.zero.service.GenreService;

import jakarta.annotation.PostConstruct;

@Controller
public class GameController {
	
	@Autowired
    private GameService gameService;


    //@PostConstruct
    public void getAllRAWGGames() throws ParseException {
        gameService.getAPIGames();
    }
    
    @GetMapping("/games/list")
    public ModelAndView list(@RequestParam(defaultValue = "1") int page) {
    	
    	ModelAndView mav = new ModelAndView("games/list");
    	
    	Collection<Game> games;
    	
    	//games = this.gameService.findAll();
    	
    	Pageable paging= PageRequest.of(page-1, 12);
    	
    	Page<Game> pGame = gameService.findAllPagining(paging);
    	
    	games = pGame.getContent();
    	
    	mav.addObject("games", games);
    	mav.addObject("currentPage", pGame.getNumber()+1);
    	mav.addObject("totalItems", pGame.getTotalElements());
    	mav.addObject("totalPages", pGame.getTotalPages());
    	mav.addObject("pageSize", 12);

		return mav;
    }
    
    
}






