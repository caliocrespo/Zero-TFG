package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zero.domain.Developer;


public interface DeveloperRepository extends JpaRepository<Developer,Integer>{

}
