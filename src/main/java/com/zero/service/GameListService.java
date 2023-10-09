package com.zero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zero.repository.GameListRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GameListService {
	
	//Own Repository
	
	@Autowired
	private GameListRepository gameListRepository;
	
	//Others repositories/services
	
	//Constructor
	
	public GameListService() {
		super();
	}
	
	//Create method
	
	//Finds method
	
	//Others method

}
