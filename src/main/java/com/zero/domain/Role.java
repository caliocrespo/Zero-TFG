package com.zero.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private ERole name;
	
	
	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}
