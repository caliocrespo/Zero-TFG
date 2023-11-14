package com.zero.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zero.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer>{
	@Query("select r from Review r where r.id = ?1")
	Review findById(int id);
	
	@Query("select r from Review r where r.progress.game.id = ?1 AND r.text IS NOT NULL")
	Collection<Review> findByGame(int gameId);
}
