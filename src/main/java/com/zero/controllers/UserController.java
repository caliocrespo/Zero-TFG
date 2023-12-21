package com.zero.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zero.domain.Game;
import com.zero.domain.GameList;
import com.zero.domain.Progress;
import com.zero.domain.Review;
import com.zero.domain.UserEntity;
import com.zero.repository.GameRepository;
import com.zero.repository.ProgressRepository;
import com.zero.service.GameListService;
import com.zero.service.GameService;
import com.zero.service.ProgressService;
import com.zero.service.ReviewService;
import com.zero.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//--------Other services------
	@Autowired
	private ProgressService progressService;
	@Autowired
	private ProgressRepository progressRepository;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private GameListService gameListService;
	
	@GetMapping("/{username}")
	public ModelAndView myProfile(@PathVariable String username) {
		ModelAndView mav;
		
		
		
		Collection<Progress> last4 = progressService.findTop4CompletedByUser(username);
		Collection<Progress> allProgress = progressService.findByUsername(username);
		Collection<GameList> gameLists = gameListService.findByUsername(username);
		Review lastReview = reviewService.findLastReview(username);
		Progress lastGame = progressService.findLastProgress(username);
		UserEntity user=userService.findByUsername(username);
		mav = new ModelAndView("/user/profile");
		
		
		mav.addObject("gameLists", gameLists);
		mav.addObject("lastGame", lastGame);
		mav.addObject("user", user);
		mav.addObject("last4", last4);
		mav.addObject("progress", allProgress);
		mav.addObject("username", username);
		mav.addObject("lastReview", lastReview);
		
		return mav;
	}
	
	@GetMapping("/{username}/games")
	public ModelAndView userGames(@PathVariable String username, @RequestParam (defaultValue="All") String status, @RequestParam (defaultValue="1") int page) {
		ModelAndView mav;
		
		Pageable paging= PageRequest.of(page-1, 30);
		
		Collection<Progress> progressList;
		Page<Progress> progressPage;
		mav = new ModelAndView("/games/myGames");
		if(status.equals("All")) {
			progressPage = progressRepository.findByUser(username, paging);
		}else {
			progressPage = progressRepository.findByUserAndStatus(username, status, paging);
		}
		
		progressList = progressPage.getContent();
		
		mav.addObject("status", status);
		mav.addObject("username", username);
		if(progressList.size() > 0) {
			mav.addObject("progressList", progressList);
		}
    	mav.addObject("currentPage", progressPage.getNumber()+1);
    	mav.addObject("totalItems", progressPage.getTotalElements());
    	mav.addObject("totalPages", progressPage.getTotalPages());
    	mav.addObject("pageSize", 30);
    	
    	return mav;
	}

}
