package com.zero.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    public ModelAndView ratedGame(String ownStatus, String ownRating, int gameId) {
		
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		UserEntity user = userService.findByUsername(name);
		Game game = gameService.findById(gameId);
		
		Progress progress = progressService.findByGameAndUser(gameId, user.getUsername());
		
		if(progress == null) {
			progress = progressService.create(game, user);
		}
		System.out.println(ownRating);
		if(!ownRating.isEmpty())
			progress.setRating(Double.parseDouble(ownRating));
		else
			progress.setRating(0.0);
		
		if(ownStatus.equals("")) {
			progress.setStatus("Playing");
		}else {
		
			int status= Integer.parseInt(ownStatus);
			
			switch(status) {
				case 1: progress.setStatus("Playing"); progress.setFinish_date(null);
				break;
				case 2:
					progress.setStatus("Completed");
					if(progress.getFinish_date() == null) {
						Date currentDate = new Date();
					    progress.setFinish_date(currentDate);
					}
				break;
				case 3: progress.setStatus("Dropped"); progress.setFinish_date(null);
				break;
				case 4: progress.setStatus("Plan To Play"); progress.setFinish_date(null);
				break;
			}
		}
		
		progressService.save(progress);
		
		return new ModelAndView("redirect:/game?progressSuccess&id=" + gameId);
    }
}
