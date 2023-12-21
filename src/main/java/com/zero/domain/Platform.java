package com.zero.domain;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)

public class Platform extends DomainEntity{
	private int idRAWG;
	private String name;
	private String slug;
	private Integer year_start;
	private String description;
	private Collection<Game> games;
	
	
	// ---------- Getters ----------
	
	public int getIdRAWG() {
		return idRAWG;
	}
	@NotBlank
	public String getName() {
		return name;
	}
	public Integer getYear_start() {
		return year_start;
	}
	@NotBlank
	public String getSlug() {
		return slug;
	}
	@Column(length=65555)
	public String getDescription() {
		return description;
	}
	
	// ---------- Setters ----------
	
	public void setDescription(String description) {
		this.description = description;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public void setIdRAWG(int idRAWG) {
		this.idRAWG = idRAWG;
	}
	public void setYear_start(Integer year_start) {
		this.year_start = year_start;
	}
	
	//---------Relationships-------------------

	@ManyToMany
	public Collection<Game> getGames() {
		return games;
	}
	public void setGames(Collection<Game> games) {
		this.games = games;
	}
	
	//----------- Adding Methods ----------------
	
	public void addGame(Game game) {
		if(this.games == null) {
			this.games = new ArrayList<>();
		}
		this.games.add(game);
	}
	
	
}
