package com.zero.auxiliar;

import java.util.Date;

public class ProgressAPI {
	
	private Integer id;
	private String status;
	private Double rating;
	private Date finish_date;
	private ReviewAPI review;
	private GameAPI game;
	private String username;	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public Double getRating() {
		return rating;
	}
	public Date getFinish_date() {
		return finish_date;
	}
	public GameAPI getGame() {
		return game;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public void setFinish_date(Date finish_date) {
		this.finish_date = finish_date;
	}
	public void setGame(GameAPI game) {
		this.game = game;
	}
	public ReviewAPI getReview() {
		return review;
	}
	public void setReview(ReviewAPI review) {
		this.review = review;
	}
	
	
}

