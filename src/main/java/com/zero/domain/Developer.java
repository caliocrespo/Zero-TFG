package com.zero.domain;

import java.util.Collection;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)

public class Developer extends DomainEntity{
	private String name;
	private String slug;
	private String description;
	private Integer founded_year;
	private Collection<Game> games;
	
	//---------- Getters ----------
	@NotBlank
	public String getName() {
		return name;
	}
	@Column(length=65555)
	public String getDescription() {
		return description;
	}
	public Integer getFounded_year() {
		return founded_year;
	}
	public String getSlug() {
		return slug;
	}
	
	// ---------- Setters ----------
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setFounded_year(Integer founded_year) {
		this.founded_year = founded_year;
	}
	
	//---------Relationships-------------------
	
	@OneToMany(mappedBy="developer")
	public Collection<Game> getGames() {
		return games;
	}
	public void setGames(Collection<Game> games) {
		this.games = games;
	}

}
