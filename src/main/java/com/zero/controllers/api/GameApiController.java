package com.zero.controllers.api;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zero.auxiliar.GameAPI;
import com.zero.domain.Game;
import com.zero.repository.GameRepository;
import com.zero.service.GameService;
import com.zero.service.GenreService;
@RestController
@RequestMapping("/api/games")
public class GameApiController {

	@Autowired
	private GameService gameService;
	
	
	@GetMapping
	public Collection<GameAPI> getGames(@RequestParam(required = false) Integer id, @RequestParam (required=false) String name,
			@RequestParam (required=false) Double minRating, @RequestParam (required = false) Double maxRating,
			@RequestParam (required = false) Integer year){
		List<GameAPI> games= gameService.gamesApi();
		
		
		
		games = games.stream().filter(game -> (id == null || game.getId() == id.intValue()) 
				&& (name == null || game.getSlug().equalsIgnoreCase(name))
				&& (minRating == null || (game.getRating() != null && game.getRating() >= minRating))
				&& (maxRating == null || (game.getRating() != null && game.getRating() <= maxRating))).collect(Collectors.toList());
		
		return  games;
	}
}
