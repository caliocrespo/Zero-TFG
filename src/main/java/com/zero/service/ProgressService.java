package com.zero.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zero.repository.ProgressRepository;
import com.zero.domain.Game;
import com.zero.domain.Progress;
import com.zero.domain.UserEntity;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProgressService {
	
	//Own Repository
	
	@Autowired
	private ProgressRepository progressRepository;
	
	//Others repositories/services
	
	@Autowired
	private GameService gameService;
	
	//Constructor
	
	public ProgressService() {
		super();
	}
	
	//Create method
	public Progress create(Game game, UserEntity user) {
		Progress progress;
		
		progress = new Progress();
		
		progress.setGame(game);
		progress.setUser(user);
		
		return progress;
	}
	
	public Progress save(Progress progress) {
		Progress result;
		
		result = progressRepository.save(progress);
		
		Game game = progress.getGame();
		if(!progress.getStatus().equals("Plan To Watch")) {
			game.setRate(this.progressRepository.findRatingByGame(game.getId()));
		}
		
		gameService.save(game);
		
		return result;
	}
	//Finds method
	
	
	public Progress findById(int progressId) {
		Progress progress;
		
		progress = progressRepository.findById(progressId);
		
		return progress;
	}
	
	public Collection<Progress> findByGame(int gameId){
		Collection<Progress> progress;
		
		progress = progressRepository.findByGame(gameId);
		
		return progress;
	}
	
	public Double findRatingByGame(int gameId) {
		Double avg_Rating;
		
		avg_Rating = progressRepository.findRatingByGame(gameId);
		
		return avg_Rating;
	}
	
	public Progress findByGameAndUser(int gameId, String username) {
		Progress progress;
		
		progress = progressRepository.findByGameAndUser(gameId, username);
		
		return progress;
	}
	
	public Collection<Progress> findByUsername(String username){
		Collection<Progress> result;
		
		result = progressRepository.findByUsername(username);
		
		return result;
	}
	
	public Collection<Progress> findTop4CompletedByUser(String username){
		Collection<Progress> result;
		
		result = progressRepository.findTop4CompletedByUser(username);
		
		return result;
	}
	
	public Progress findLastProgress(String username) {
		Progress result;
		
		result = progressRepository.findLastProgress(username);
		
		return result;
	}
	
	//Others method

}
