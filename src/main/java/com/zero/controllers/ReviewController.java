package com.zero.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.zero.domain.Progress;
import com.zero.domain.Review;
import com.zero.service.ProgressService;
import com.zero.service.ReviewService;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	//------other services--------
	
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
	
	@PostMapping("/review/save")
	public ModelAndView save(int reviewId, String textReview, int progressId) {
				
		Review review;
		
		review=reviewService.findById(reviewId);
		
		review.setText(textReview);
		
		this.reviewService.save(review);
		
		return new ModelAndView("redirect:/game?reviewSuccess&id=" + review.getProgress().getGame().getId());
		
	}
}
