package com.zero.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zero.domain.Progress;
import com.zero.domain.Review;
import com.zero.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReviewService {
	
	//Own Repository
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	//Others repositories/services
	@Autowired
	private ProgressService progressService;
	
	
	//Constructor
	
	public ReviewService() {
		super();
	}
	
	//Create method
	
	public Review create() {
		Review review; 
		
		review = new Review();
		
		return review;
	}
	
	public Review save(Review review) {
		Review result;
		
		result=this.reviewRepository.save(review);
		
		return result;
		
	}
	
	public Review save(Review review, int progressId) {
		Review result;
		
		Progress progress = progressService.findById(progressId);
		
		review.setProgress(progress);
		
		result=this.reviewRepository.save(review);
		
		return result;
	}
	
	//Finds method
	
	public Review findById(int id) {
		Review review;
		
		review = reviewRepository.findById(id);
		
		return review;		
	}
	
	public Collection<Review> findByGame(int gameId){
		Collection<Review> reviews;
		
		reviews = reviewRepository.findByGame(gameId);
		
		return reviews;
	}

	
	
	//Others method

}
