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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;

import com.zero.domain.Developer;
import com.zero.domain.Game;
import com.zero.domain.GameList;
import com.zero.domain.Genre;
import com.zero.domain.Platform;
import com.zero.domain.Progress;
import com.zero.domain.Review;
import com.zero.repository.GameRepository;
import com.zero.repository.GenreRepository;
import com.zero.repository.ProgressRepository;
import com.zero.repository.ReviewRepository;
import com.zero.service.DeveloperService;
import com.zero.service.GameListService;
import com.zero.service.GameService;
import com.zero.service.GenreService;
import com.zero.service.PlatformService;
import com.zero.service.ProgressService;
import com.zero.service.ReviewService;

import jakarta.annotation.PostConstruct;

@RestController
public class GameController {
	
	@Autowired
    private GameService gameService;
	@Autowired
	private GameRepository gameRepository;
	
	//Other Services
	@Autowired
	private ProgressService progressService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private GenreService genreService;
	@Autowired
	private PlatformService platformService;
	@Autowired
	private DeveloperService developerService;
	@Autowired
	private GameListService gameListService;
	@Autowired
	private ProgressRepository progressRepository;


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
    	Collection<Review> reviews = reviewService.findByGame(id);
    	
    	if(progress.isEmpty()) {
    		 mav.addObject("progressCount", "0");
    		 mav.addObject("reviewsCount", "0");
    		 mav.addObject("rating", "-");
    	}else {
    		mav.addObject("progressCount", Integer.toString(progress.size()));
    		mav.addObject("reviewsCount", Integer.toString(reviews.size()));
    		if(progressService.findRatingByGame(id) != null) {
    			String rating = String.format("%.1f",progressService.findRatingByGame(id));
    			mav.addObject("rating", rating);
    		}else {
    			mav.addObject("rating", "-");
    		}
    		

    	}
    	
    	if(reviews.size() > 0) {
    		mav.addObject("reviews", reviews);
    	}
    	
    	
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	Progress ownProgress = progressService.findByGameAndUser(id, username);
    	Collection<GameList> gameLists = gameListService.findByUsername(username);
    	if(ownProgress!=null) {
    		mav.addObject("status", ownProgress.getStatus());
    		mav.addObject("ownRating", ownProgress.getRating());
    		mav.addObject("progressId", ownProgress.getId());
    		mav.addObject("ownReview", ownProgress.getReview());
    	}
    	
    	
    	
    	Collection<Platform> platforms = game.getPlatforms();
    	Collection<Genre> genres=game.getGenres();
    	
    	if(!gameLists.isEmpty()) {
    		mav.addObject("gameLists", gameLists);
    	}
    	
    	
    	mav.addObject("game", game);
    	mav.addObject("platforms", platforms);
    	mav.addObject("genres", genres);
    	
    	return mav;
    }
    
    @GetMapping("/games/list")
    public ModelAndView list(@RequestParam(defaultValue = "1") int page) {
    	
    	ModelAndView mav = new ModelAndView("games/list");
    	
    	Collection<Game> games;
    	    	
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
    
    @GetMapping("/games/listByDeveloper")
    public ModelAndView listByDeveloper(@RequestParam int developerId, @RequestParam(defaultValue = "1") int page) {
    	ModelAndView mav;
    	
    	mav = new ModelAndView("/games/list");
    	
    	Collection<Game> games;
    	Developer developer = developerService.findById(developerId);
    	
    	Pageable paging= PageRequest.of(page-1, 12);
    	
    	Page<Game> pGame = gameRepository.findByDeveloper(developerId, paging) ;
    	
    	games = pGame.getContent();
    	
    	mav.addObject("developer", developer);
    	mav.addObject("games", games);
    	mav.addObject("currentPage", pGame.getNumber()+1);
    	mav.addObject("totalItems", pGame.getTotalElements());
    	mav.addObject("totalPages", pGame.getTotalPages());
    	mav.addObject("pageSize", 12);
    	
    	return mav;
    	
    }
    
    @GetMapping("/games/listByGenre")
    public ModelAndView listByGenre(@RequestParam int genreId, @RequestParam(defaultValue = "1") int page) {
    	ModelAndView mav;
    	
    	mav = new ModelAndView("/games/list");
    	
    	Collection<Game> games;
    	Genre genre = genreService.findById(genreId);
    	
    	Pageable paging= PageRequest.of(page-1, 12);
    	
    	Page<Game> pGame = gameRepository.findByGenrePage(genreId, paging) ;
    	
    	games = pGame.getContent();
    	
    	mav.addObject("genre", genre);
    	mav.addObject("games", games);
    	mav.addObject("currentPage", pGame.getNumber()+1);
    	mav.addObject("totalItems", pGame.getTotalElements());
    	mav.addObject("totalPages", pGame.getTotalPages());
    	mav.addObject("pageSize", 12);
    	
    	return mav;
    	
    }
    
    @GetMapping("/games/listByPlatform")
    public ModelAndView listByPlatform(@RequestParam int platformId, @RequestParam(defaultValue = "1") int page) {
    	ModelAndView mav;
    	
    	mav = new ModelAndView("/games/list");
    	
    	Collection<Game> games;
    	Platform platform = this.platformService.findById(platformId);
    	
    	Pageable paging= PageRequest.of(page-1, 12);
    	
    	Page<Game> pGame = gameRepository.findByPlatform(platformId, paging) ;
    	
    	games = pGame.getContent();
    	
    	mav.addObject("platform", platform);
    	mav.addObject("games", games);
    	mav.addObject("currentPage", pGame.getNumber()+1);
    	mav.addObject("totalItems", pGame.getTotalElements());
    	mav.addObject("totalPages", pGame.getTotalPages());
    	mav.addObject("pageSize", 12);
    	
    	return mav;
    }
    
    @GetMapping("/games/listByGameList")
    public ModelAndView listByGameList(@RequestParam int gameListId, @RequestParam(defaultValue = "1") int page) {
    	ModelAndView mav;
    	
    	mav = new ModelAndView("/games/list");
    	
    	Collection<Game> games;
    	GameList gameList = this.gameListService.findById(gameListId);
    	
    	
    	games = gameList.getGames();
    	
    	
    	
    	mav.addObject("gameList", gameList);
    	mav.addObject("games", games);
    	
    	return mav;
    }
    
    @GetMapping("/myGames")
    public ModelAndView myGames(@RequestParam(defaultValue = "1") int page) {
    	ModelAndView mav;
    	
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	
    	mav = new ModelAndView("/games/myGames");
    	
    	Collection<Progress> progressList;
    	
    	Pageable paging= PageRequest.of(page-1, 30);
    	
    	Page<Progress> progressPage = progressRepository.findByUser(username, paging);
    	
    	progressList = progressPage.getContent();
    	
    	mav.addObject("status", "All");
    	mav.addObject("username", username);
    	mav.addObject("progressList", progressList);
    	mav.addObject("currentPage", progressPage.getNumber()+1);
    	mav.addObject("totalItems", progressPage.getTotalElements());
    	mav.addObject("totalPages", progressPage.getTotalPages());
    	mav.addObject("pageSize", 30);
    	
    	return mav;
    }
    
    
}






