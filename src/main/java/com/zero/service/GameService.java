package com.zero.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.zero.auxiliar.GameAPI;
import com.zero.auxiliar.GameList;
import com.zero.auxiliar.PlatformAPI;
import com.zero.auxiliar.PlatformListAPI;
import com.zero.domain.Developer;
import com.zero.domain.Game;
import com.zero.domain.Platform;
import com.zero.domain.Genre;
import com.zero.repository.GameRepository;
import com.zero.repository.PlatformRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GameService {

	private final String RAWG_URL = "https://api.rawg.io/api/games?key=e63df09f5ae744498fb5a5ee6d3ca236";

	@Autowired
	private final RestTemplate restTemplate;
	// Own Repository

	@Autowired
	private GameRepository gameRepository;

	// Others repositories/services
	@Autowired
	private PlatformRepository platformRepository;

	@Autowired
	private GenreService genreService;
	
	@Autowired
	private DeveloperService developerService;
	// Constructor

	public GameService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	// Create method

	public Game save(Game game) {
		Game result;

		result = gameRepository.save(game);

		return result;
	}

	// Finds method

	public Collection<Game> findAll() {
		Collection<Game> result;

		result = this.gameRepository.findAll();

		Assert.notNull(result, "Should not be null");

		return result;
	}

	public Page<Game> findAllPagining(Pageable paging) {
		Page<Game> pGame;

		pGame = gameRepository.findAll(paging);

		return pGame;
	}

	public Game findById(int gameId) {
		Game game;

		game = gameRepository.findById(gameId);

		return game;
	}

	// Others method

	public void getAPIGames() throws ParseException {
		/*
		 * int page=1;
		 * 
		 * while(true) { ResponseEntity<GameList> responseEntity=
		 * restTemplate.exchange(RAWG_URL + "&page=" + page + "&page_size=" + 21,
		 * HttpMethod.GET,null,GameList.class);
		 * 
		 * if(responseEntity.getStatusCode().is2xxSuccessful() && page < 2 ) { GameList
		 * gameList = responseEntity.getBody();
		 * 
		 * if(gameList!=null && gameList.getResults()!=null) { List<GameAPI> games =
		 * gameList.getResults();
		 * 
		 * for(GameAPI gAPI : games) { String RAWG_URL2=
		 * "https://api.rawg.io/api/games/"+ gAPI.getId()+
		 * "?key=e63df09f5ae744498fb5a5ee6d3ca236";
		 * 
		 * ResponseEntity<GameAPI> responseEntity2= restTemplate.exchange(RAWG_URL2,
		 * HttpMethod.GET,null,GameAPI.class);
		 * 
		 * GameAPI aux = responseEntity2.getBody();
		 * 
		 * if(gameRepository.findBySlug(aux.getSlug())==null) {
		 * 
		 * Game addGame = new Game();
		 * 
		 * addGame.setTitle(aux.getName());
		 * addGame.setDescription(aux.getDescription());
		 * addGame.setImage(aux.getBackgroundImage()); addGame.setSlug(aux.getSlug());
		 * 
		 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); Date dateAux =
		 * sdf.parse(aux.getReleased()); addGame.setRelease_date(dateAux);
		 * gameRepository.save(addGame); for(PlatformListAPI platform :
		 * aux.getPlatforms()) { Platform platformGame =
		 * platformRepository.findByRAWGId(platform.getPlatform().getId());
		 * if(platformGame != null) { addGame.addPlatform(platformGame); } }
		 * 
		 * } } } page=page+1; }else { System.out.println("finish"); break; }
		 * 
		 * }
		 */
		this.getGamesGenres();
		this.getGamesDevelopers();

	}

	public void getGamesGenres() throws ParseException {

		//Todos los géneros
		List<Genre> genres = (List<Genre>) genreService.findAll();
		
		//por cada género busca 10 juegos
		for (Genre genre : genres) {
			int page = 1;
			ResponseEntity<GameList> responseEntity = restTemplate.exchange(
					RAWG_URL + "&page=" + page + "&page_size=" + 5 + "&genres=" + genre.getSlug(), HttpMethod.GET, null,
					GameList.class);
			if (responseEntity.getStatusCode().is2xxSuccessful() && page < 2) {
				GameList gameList = responseEntity.getBody();
				
				//comprueba si ha extraído algo
				if (gameList != null && gameList.getResults() != null) {
					List<GameAPI> games = gameList.getResults();
					
					//obtiene cada juego
					for (GameAPI gAPI : games) {
						String RAWG_URL2 = "https://api.rawg.io/api/games/" + gAPI.getId()
								+ "?key=e63df09f5ae744498fb5a5ee6d3ca236";

						ResponseEntity<GameAPI> responseEntity2 = restTemplate.exchange(RAWG_URL2, HttpMethod.GET, null,
								GameAPI.class);

						GameAPI aux = responseEntity2.getBody();

						Game currentGame = gameRepository.findBySlug(aux.getSlug());
						
						//comprueba si ya existe el juego en nuestra BD
						if (currentGame == null) {
							this.addNewGame_Genre(aux, genre);
						} else {
							currentGame.addGenre(genre);
							this.save(currentGame);
						}
					}
				}
				page = page + 1;
			}
		}

	}
	
	public void getGamesDevelopers() throws ParseException {

		List<Developer> developers = (List<Developer>) developerService.findAll();
		
		for (Developer developer : developers) {
			int page = 1;
			ResponseEntity<GameList> responseEntity = restTemplate.exchange(
					RAWG_URL + "&page=" + page + "&page_size=" + 5 + "&developers=" + developer.getSlug(), HttpMethod.GET, null,
					GameList.class);
			
			if (responseEntity.getStatusCode().is2xxSuccessful() && page < 2) {
				GameList gameList = responseEntity.getBody();

				if (gameList != null && gameList.getResults() != null) {
					List<GameAPI> games = gameList.getResults();

					for (GameAPI gAPI : games) {
						String RAWG_URL2 = "https://api.rawg.io/api/games/" + gAPI.getId()
								+ "?key=e63df09f5ae744498fb5a5ee6d3ca236";

						ResponseEntity<GameAPI> responseEntity2 = restTemplate.exchange(RAWG_URL2, HttpMethod.GET, null,
								GameAPI.class);

						GameAPI aux = responseEntity2.getBody();

						Game currentGame = gameRepository.findBySlug(aux.getSlug());

						if (currentGame == null) {
							this.addNewGame_Developer(aux, developer);
						} else {
							currentGame.addDeveloper(developer);
							this.save(currentGame);
						}
					}
				}
				page = page + 1;
			}
		}

	}

	public void addNewGame_Genre(GameAPI aux, Genre genre) throws ParseException {
		Game addGame = new Game();

		addGame.setTitle(aux.getName());
		addGame.setDescription(aux.getDescription());
		addGame.setImage(aux.getBackgroundImage());
		addGame.setSlug(aux.getSlug());
		addGame.addGenre(genre);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateAux = sdf.parse(aux.getReleased());
		addGame.setRelease_date(dateAux);
		gameRepository.save(addGame);
		for (PlatformListAPI platform : aux.getPlatforms()) {
			Platform platformGame = platformRepository.findByRAWGId(platform.getPlatform().getId());
			if (platformGame != null) {
				addGame.addPlatform(platformGame);
			}
		}
	}
	
	public void addNewGame_Developer(GameAPI aux, Developer developer) throws ParseException {
		Game addGame = new Game();

		addGame.setTitle(aux.getName());
		addGame.setDescription(aux.getDescription());
		addGame.setImage(aux.getBackgroundImage());
		addGame.setSlug(aux.getSlug());
		addGame.addDeveloper(developer);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateAux = sdf.parse(aux.getReleased());
		addGame.setRelease_date(dateAux);
		gameRepository.save(addGame);
		for (PlatformListAPI platform : aux.getPlatforms()) {
			Platform platformGame = platformRepository.findByRAWGId(platform.getPlatform().getId());
			if (platformGame != null) {
				addGame.addPlatform(platformGame);
			}
		}
	}

}
