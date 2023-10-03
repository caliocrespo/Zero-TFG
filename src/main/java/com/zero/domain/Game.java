package com.zero.domain;

import java.util.Date;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)

public class Game extends DomainEntity{
	private String title;
	private String description;
	private String image;
	private Float range;
	private Date release_date;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	@NotBlank
	public String getDescription() {
		return description;
	}
	@URL
	public String getImage() {
		return image;
	}
	
	@Range(min=(long) 0.5, max=5)
	public Float getRange() {
		return range;
	}
	public Date getRelease_date() {
		return release_date;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setRange(Float range) {
		this.range = range;
	}
	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}
	
	
}
