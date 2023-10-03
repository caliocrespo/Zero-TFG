package com.zero.domain;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.*;

@Entity
@Access(AccessType.PROPERTY)

public class Review extends DomainEntity{
	private String text;

	@Range(min=(long) 0.5, max=5)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
