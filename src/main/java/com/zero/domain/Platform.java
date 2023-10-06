package com.zero.domain;

import java.util.Collection;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)

public class Platform extends DomainEntity{

	private String name;
	private Integer year_start;
	private String description;
	private Collection<Game> games;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@NotBlank
	public String getName() {
		return name;
	}
	public Integer getRelease_year() {
		return year_start;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRelease_year(Integer release_year) {
		this.year_start = release_year;
	}
	
	//---------Relationships-------------------

	@ManyToMany
	public Collection<Game> getGames() {
		return games;
	}
	public void setGames(Collection<Game> games) {
		this.games = games;
	}
	
	
}
