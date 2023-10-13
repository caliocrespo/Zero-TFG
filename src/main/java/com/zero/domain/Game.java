package com.zero.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)

public class Game extends DomainEntity{
	private String title;
	private String slug;
	private String description;
	private String image;
	//private Integer rate;
	private Date release_date;
	private Collection<Progress> progress;
	private Developer developer;
	private Collection<Genre> genres;
	private Collection<Platform> platforms;
	
	//---------- Getters ----------
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public String getSlug() {
		return slug;
	}
	@Column(length=65555)
	@NotBlank
	public String getDescription() {
		return description;
	}
	@URL
	public String getImage() {
		return image;
	}
	public Date getRelease_date() {
		return release_date;
	}
	
	// ---------- Setters ----------
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}
	
	//---------Relationships-------------------
	
	@OneToMany(mappedBy="game")
	public Collection<Progress> getProgress() {
		return progress;
	}
	public void setProgress(Collection<Progress> progress) {
		this.progress = progress;
	}
	
	
	@ManyToOne(optional=true)
	public Developer getDeveloper() {
		return developer;
	}
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	
	
	@ManyToMany(mappedBy="games")
	public Collection<Genre> getGenres() {
		return genres;
	}	
	public void setGenres(Collection<Genre> genres) {
		this.genres = genres;
	}
	
	
	@ManyToMany(mappedBy="games")
	@Valid
	public Collection<Platform> getPlatforms() {
		return platforms;
	}
	public void setPlatforms(Collection<Platform> platforms) {
		this.platforms = platforms;
	}
	
	//----------- Adding Methods ----------------
	
	public void addPlatform(Platform platform) {
		if(this.platforms == null) {
			platforms = new ArrayList<>();
		}
		
		this.platforms.add(platform);
		
		platform.addGame(this);
	}
	
	
}
