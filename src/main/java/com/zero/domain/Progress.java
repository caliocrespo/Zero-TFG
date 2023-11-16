package com.zero.domain;

import java.util.Date;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)

public class Progress extends DomainEntity{
	private String status;
	private Double rating;
	private Date finish_date;
	private Review review;
	private Game game;
	private UserEntity user;
	
	//---------- Getters ----------
	
	@NotBlank
	public String getStatus() {
		return status;
	}
	@Range(min= 0, max=5)
	public Double getRating() {
		return rating;
	}
	public Date getFinish_date() {
		return finish_date;
	}
	
	// ---------- Setters ----------
	
	public void setStatus(String status) {
		this.status = status;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public void setFinish_date(Date finish_date) {
		this.finish_date = finish_date;
	}
	
	//---------Relationships------------------
	
	@OneToOne(optional=true,mappedBy="progress")
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	
	@ManyToOne(optional=false)
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	@ManyToOne(optional=false)
	public UserEntity getUser() {
		return user;
	}
	
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	
}
