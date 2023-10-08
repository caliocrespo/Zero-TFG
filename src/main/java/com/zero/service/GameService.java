package com.zero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zero.auxiliar.GameAPI;
import com.zero.auxiliar.GameList;
import com.zero.domain.Game;
import com.zero.repository.GameRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GameService {
	
	private final String RAWG_URL= "https://api.rawg.io/api/games?key=bc332b7707a14fefbfbdc66dc64bb588";
	
	@Autowired
	private final RestTemplate restTemplate;
	//Own Repository
	
	@Autowired
	private GameRepository gameRepository;
	
	//Others repositories/services
	
	//Constructor
	
	public GameService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	//Create method
	
	//Finds method
	
	//Others method
	
	public void getAPIGames() {
		int n=1;
		
		while(true) {
			ResponseEntity<GameList> responseEntity= restTemplate.exchange(RAWG_URL + "&developers=2k" + "&page=" + n + "&page_size=" + 1,
					HttpMethod.GET,null,GameList.class);
			
			if(responseEntity.getStatusCode() == HttpStatus.OK && n < responseEntity.getBody().getCount() ) {
				GameList gameList = responseEntity.getBody();
				
				if(gameList!=null && gameList.getResults()!=null) {
					List<GameAPI> games = gameList.getResults();
					
					for(GameAPI gAPI : games) {
						System.out.println(gAPI.getName());
					}
				}
				n=n+1;
			}else {
				System.out.println("finish");
				break;
			}
			
		}
		
	}

}
