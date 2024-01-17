package com.zero.auxiliar;

import java.util.ArrayList;
import java.util.Collection;

public class ListAPI {
	private Integer id;
    private String title;
    private String description;
    private Integer games_count;
    private String user;
    private Collection<GameAPI> games;
    
    public ListAPI() {
    	games = new ArrayList<>();
    }
    
    public Integer getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}


	public String getDescription() {
		return description;
	}


	public Integer getGames_count() {
		return games_count;
	}


	public Collection<GameAPI> getGames() {
		return games;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setGames_count(Integer games_count) {
		this.games_count = games_count;
	}


	public void setGames(Collection<GameAPI> games) {
		this.games = games;
	}


	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void addGame(GameAPI game) {
		games.add(game);
	}
	
    
}
    
    
	