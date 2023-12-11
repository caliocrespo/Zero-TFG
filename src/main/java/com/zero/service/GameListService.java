package com.zero.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zero.domain.Game;
import com.zero.domain.GameList;
import com.zero.domain.UserEntity;
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
	
	public GameList create(UserEntity user) {
		GameList gameList;
		
		gameList = new GameList();
		
		gameList.setUser(user);
		
		return gameList;
	}

	public GameList save(GameList gameList) {
		GameList result = gameList;
		
		this.gameListRepository.save(gameList);
		
		return result;		
	}
	
	//Finds method
	
	public GameList findById(int id) {
		GameList result;
		
		result = this.gameListRepository.findById(id);
		
		return result;
	}
	
	public Collection<GameList> findByUsername(String username){
		Collection<GameList> result;
		
		result = this.gameListRepository.findByUsername(username);
		
		return result;
	}


	public void delete(GameList gameList) {
		this.gameListRepository.delete(gameList);
		
	}


	public void deleteGame(GameList gameList, Game game) {
		gameList.deleteGame(game);
		
		this.save(gameList);		
	}
	
	//Others method

}
