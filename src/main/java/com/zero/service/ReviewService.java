package com.zero.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zero.auxiliar.ReviewAPI;
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
		
		
		result=this.reviewRepository.save(review);
		
		progress.setReview(review);
		
		this.progressService.save(progress);
		
		return result;
	}
	
	public void delete(Review review) {
		
		
		
		this.reviewRepository.delete(review);
		
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
	
	public ReviewAPI transformToAPI(Review review) {
		ReviewAPI result = new ReviewAPI();
		
		result.setId(review.getId());
		result.setText(review.getText());
		
		return result;		
	}

	

}
