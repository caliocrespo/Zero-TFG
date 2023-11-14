package com.zero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zero.domain.Game;
import com.zero.domain.Progress;
import com.zero.domain.UserEntity;
import com.zero.service.GameService;
import com.zero.service.ProgressService;
import com.zero.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ProgressController {

	@Autowired
	private ProgressService progressService;
	
	//--------Other services------------
	@Autowired
	UserService userService;
	@Autowired
	GameService gameService;
	
	
	
	@PostMapping("/progress/rated")
    public ModelAndView ratedGame(String selected, String ownRating, int gameId) {
		
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity user = userService.findByUsername(name);
		Game game = gameService.findById(gameId);
		
		Progress progress = progressService.findByGameAndUser(gameId, user.getUsername());
		
		if(progress == null) {
			progress = progressService.create(game, user);
		}
		System.out.println(ownRating);
		progress.setRating(Double.parseDouble(ownRating));
		progress.setStatus(selected);
		
		progressService.save(progress);
		
		return new ModelAndView("redirect:/game?progressSuccess&id=" + gameId);
    }
}
