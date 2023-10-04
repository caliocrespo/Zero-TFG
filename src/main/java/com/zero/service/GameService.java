package com.zero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zero.repository.GameRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GameService {
	
	//Own Repository
	
	@Autowired
	private GameRepository gameRepository;
	
	//Others repositories/services
	
	//Constructor
	
	public GameService() {
		super();
	}
	
	//Create method
	
	//Finds method
	
	//Others method

}
