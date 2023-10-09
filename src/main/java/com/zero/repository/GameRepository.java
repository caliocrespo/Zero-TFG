package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zero.domain.Game;

@Repository
public interface GameRepository extends JpaRepository<Game,Integer>{
	@Query("select g from Game g where g.slug = ?1 ")
	Game findBySlug (String slugGame);
	
}
