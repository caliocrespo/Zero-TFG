package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zero.domain.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Integer>{

}
