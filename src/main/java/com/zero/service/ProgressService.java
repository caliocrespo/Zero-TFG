package com.zero.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zero.repository.ProgressRepository;

import com.zero.domain.Progress;

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
	
	public Collection<Progress> findByGame(int gameId){
		Collection<Progress> progress;
		
		progress = progressRepository.findByGame(gameId);
		
		return progress;
	}
	
	//Others method

}
