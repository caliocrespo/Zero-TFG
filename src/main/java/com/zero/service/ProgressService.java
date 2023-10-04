package com.zero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zero.repository.ProgressRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProgressService {
	
	//Own Repository
	
	@Autowired
	private ProgressRepository progressRepository;
	
	//Others repositories/services
	
	//Constructor
	
	public ProgressService() {
		super();
	}
	
	//Create method
	
	//Finds method
	
	//Others method

}
