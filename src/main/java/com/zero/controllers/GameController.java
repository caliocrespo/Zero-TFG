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

import com.zero.domain.Developer;
import com.zero.domain.Game;
import com.zero.domain.Genre;
import com.zero.domain.Platform;
import com.zero.domain.Progress;
import com.zero.domain.Review;
import com.zero.service.GameService;
import com.zero.service.GenreService;
import com.zero.service.ProgressService;

import jakarta.annotation.PostConstruct;

@Controller
public class GameController {
	
	@Autowired
    private GameService gameService;
	
	//Other Services
	@Autowired
	private ProgressService progressService;


    //@PostConstruct
    public void getAllRAWGGames() throws ParseException {
        gameService.getAPIGames();
    }
    
    
    @GetMapping("/game")
    public ModelAndView game(@RequestParam int id) {
    	ModelAndView mav;
    	
    	Game game=gameService.findById(id);
    	
    	mav = new ModelAndView("/game");
    	
    	Collection<Developer> developers;
    	if(game.getDevelopers()!=null) {
    		developers = game.getDevelopers();

        	mav.addObject("developers", developers);
    	}
    	
    	Collection<Progress> progress = progressService.findByGame(id);
    	
    	if(progress.isEmpty()) {
    		 mav.addObject("progressCount", "0");
    		 mav.addObject("reviewsCount", "0");
    	}else {
    		mav.addObject("progressCount", Integer.toString(progress.size()));
    	}
    	
    	
    	
    	Collection<Platform> platforms = game.getPlatforms();
    	Collection<Genre> genres=game.getGenres();
    	
    	mav.addObject("game", game);
    	mav.addObject("platforms", platforms);
    	mav.addObject("genres", genres);
    	
    	return mav;
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






