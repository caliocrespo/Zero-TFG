package com.zero.domain;

import java.util.Date;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)

public class Progress extends DomainEntity{
	private String status;
	private Float rating;
	private Date finish_date;
	
	@NotBlank
	public String getStatus() {
		return status;
	}
	@Range(min=(long) 0.5, max=5)
	public Float getRating() {
		return rating;
	}
	public Date getFinish_date() {
		return finish_date;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	public void setFinish_date(Date finish_date) {
		this.finish_date = finish_date;
	}
	
	
}
