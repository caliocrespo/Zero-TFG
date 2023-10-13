package com.zero.domain;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.*;

@Entity
@Access(AccessType.PROPERTY)

public class Review extends DomainEntity{
	private String text;
	private Progress progress;

	//---------- Getters ----------
	
	@Range(min=(long) 0.5, max=5)
	public String getText() {
		return text;
	}
	
	// ---------- Setters ----------
	
	public void setText(String text) {
		this.text = text;
	}
	
	
	//Relationships
	
	@OneToOne(optional=false)
	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}
	
	
}
