package com.zero.controllers.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zero.service.GameListService;
import com.zero.service.ProgressService;
import com.zero.auxiliar.ListAPI;
import com.zero.auxiliar.ProgressAPI;

@RestController
@RequestMapping("/api/gameLists")
public class GameListAPIController {

	@Autowired
	GameListService gameListService;
	
	@GetMapping
	public List<ListAPI> getProgress(@RequestParam(required = false) Integer id, @RequestParam (required=false) String title,
									 @RequestParam (required = false) String username){
		
		List<ListAPI> gameLists = this.gameListService.gameListAPI();
		
		gameLists = gameLists.stream().filter(gameList -> (id == null || gameList.getId() == id.intValue())
				&& (title == null || gameList.getTitle().equalsIgnoreCase(title))
				&& (username == null || gameList.getUser().equalsIgnoreCase(username))).collect(Collectors.toList());
		
		return gameLists;
	}
}
