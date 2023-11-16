package com.zero.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zero.domain.Game;

@Repository
public interface GameRepository extends JpaRepository<Game,Integer>{
	@Query("select g from Game g where g.slug = ?1 ")
	Game findBySlug (String slugGame);
	
	Page<Game> findAll(Pageable pageable);
	
	@Query("select g from Game g where g.id = ?1")
	Game findById(int gameId);
	
	@Query("select g from Game g JOIN g.developers developer where developer.id = ?1")
	Page<Game> findByDeveloper(int developerId, Pageable pageable);
	
	@Query("select g from Game g JOIN g.platforms platform where platform.id = ?1")
	Page<Game> findByPlatform( int platformId, Pageable pageable);
	
	@Query("select  g from Game g JOIN g.genres genre where genre.id= ?1")
	Page<Game> findByGenrePage(int genreId, Pageable pageable);
		
}
