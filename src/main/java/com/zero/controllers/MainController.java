package com.zero.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zero.service.GameListService;
import com.zero.service.GameService;
import com.zero.domain.Game;
import com.zero.domain.GameList;

@Controller
public class MainController {
	
	//Other services
	@Autowired
	private GameService gameService;
	@Autowired
	private GameListService gameListService;
	
	public MainController () {
		super();
	}
	
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("/index");
		
		Collection<Game> games= this.gameService.findOrderByProgress();
		List<GameList> AllLists= (List<GameList>) this.gameListService.findAllPublic();
		
		List<GameList> gameLists = AllLists.subList(Math.max(AllLists.size() - 3, 0), AllLists.size());
		
		Collections.reverse(gameLists);
		
		mav.addObject("gameLists", gameLists);
		mav.addObject("games", games);
		return mav;
	}
	
}
