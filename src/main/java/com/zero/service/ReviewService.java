package com.zero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zero.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReviewService {
	
	//Own Repository
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	//Others repositories/services
	
	//Constructor
	
	public ReviewService() {
		super();
	}
	
	//Create method
	
	//Finds method
	
	//Others method

}
