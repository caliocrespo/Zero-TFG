package com.zero.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zero.domain.Game;
import com.zero.domain.GameList;
import com.zero.domain.UserEntity;
import com.zero.repository.GameListRepository;
import com.zero.service.GameListService;
import com.zero.service.GameService;
import com.zero.service.UserService;

@Controller
public class GameListController {
	
	@Autowired
	private GameListService gameListService; 
	@Autowired
	private GameListRepository gameListRepository;
	
	//--------Other services--------
	@Autowired
	private UserService userService;
	@Autowired
	private GameService gameService;
	
	@GetMapping("/list")
	public String list(Model model) {
		
		
		
		return "/gameList/list";
	}
	
	@GetMapping("/myLists")
	public ModelAndView myLists(@RequestParam(defaultValue = "1") int page) {
		ModelAndView mav;
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		Collection<GameList> gameLists;
		
		Pageable paging= PageRequest.of(page-1, 5);
		
		Page<GameList> pList = this.gameListRepository.findByUsernamePage(username, paging);
		
		gameLists = pList.getContent();
		
		mav = new ModelAndView("gameList/myLists");
		System.out.println(gameLists.size());
		mav.addObject("gameLists", gameLists);
		mav.addObject("currentPage", pList.getNumber()+1);
    	mav.addObject("totalItems", pList.getTotalElements());
    	mav.addObject("totalPages", pList.getTotalPages());
    	mav.addObject("pageSize", 5);		
		
		return mav;
	}
	
	
	@GetMapping("/newList")
	public ModelAndView newList() {
		ModelAndView mav;
		
		mav = new ModelAndView("/gameList/newList");
		
		return mav;
	}
	
	@PostMapping("/gameList/save")
	public ModelAndView save(String name, String shared, String description) {
	
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		UserEntity user = userService.findByUsername(username);
		
		GameList gameList = gameListService.create(user);
		
		gameList.setTitle(name);
		
		if(shared.equals("Public")) {
			gameList.setShared(true);
		}
		else {
			gameList.setShared(false);
		}
		System.out.println(shared);
		
		if(description != null) {
			gameList.setDescription(description);
		}
		
		this.gameListService.save(gameList);
		
		return new ModelAndView("redirect:/myLists");
	}
	
	@PostMapping("/gameList/saveGame")
	public ModelAndView saveGame(@RequestParam(name = "gameListIds", required = false) List<Integer> selectedGameListIds, int gameId) {
		GameList gameList;
		Game game = gameService.findById(gameId);
		for(int id : selectedGameListIds) {
			gameList = gameListService.findById(id);
			gameList.addGame(game);
			gameListService.save(gameList);
		}
		
		return new ModelAndView("redirect:/game?id=" + gameId);
		
	}
	
	public boolean gameInGameList(Game game, GameList gameList) {
		return false;
	}
}
