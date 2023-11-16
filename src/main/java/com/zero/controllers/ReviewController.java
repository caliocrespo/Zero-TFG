package com.zero.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zero.domain.Game;
import com.zero.domain.Progress;
import com.zero.domain.Review;
import com.zero.repository.ReviewRepository;
import com.zero.service.GameService;
import com.zero.service.ProgressService;
import com.zero.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	@Autowired
	private ReviewRepository reviewRepository;
	//------other services--------
	@Autowired
	private GameService gameService;
	
	@Autowired
	ProgressService progressService;
	
	@GetMapping("/review")
	public ModelAndView review(@RequestParam int id) {//progress id
		ModelAndView mav;
		
		mav= new ModelAndView("/review");
		
		Progress progress= progressService.findById(id);
		
		mav.addObject("game", progress.getGame());
		
		Review review;
		if(progress.getReview() == null) {
			review=this.reviewService.create();
			this.reviewService.save(review, progress.getId());
		}else {
			review=progress.getReview();
			mav.addObject("text", review.getText());
		}
		
		mav.addObject("reviewId", review.getId());
		mav.addObject("progressId", progress.getId());
		
		
		return mav;
	}
	
	@GetMapping("/reviews/list")
	public ModelAndView list(@RequestParam int gameId, @RequestParam(defaultValue= "1") int page) {
		ModelAndView mav;
		
		Collection<Review> reviews;
		Game game;
		
		game=gameService.findById(gameId);
		
		mav = new ModelAndView("/reviews/list");
		
		Pageable paging= PageRequest.of(page-1, 10);
		
		Page<Review> pReview = reviewRepository.findByGamePage(gameId, paging);
		
		reviews = pReview.getContent();
		
		mav.addObject("reviews", reviews);
		mav.addObject("game", game);
		mav.addObject("currentPage", pReview.getNumber()+1);
    	mav.addObject("totalItems", pReview.getTotalElements());
    	mav.addObject("totalPages", pReview.getTotalPages());
    	mav.addObject("pageSize", 10);
		
    	return mav;
	}
	
	@PostMapping("/review/save")
	public ModelAndView save(int reviewId, String textReview, int progressId) {
				
		Review review;
		
		review=reviewService.findById(reviewId);
		
		review.setText(textReview);
		
		this.reviewService.save(review);
		
		return new ModelAndView("redirect:/game?reviewSuccess&id=" + review.getProgress().getGame().getId());
		
	}
}
