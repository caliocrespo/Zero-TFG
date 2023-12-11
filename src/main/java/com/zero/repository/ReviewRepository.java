package com.zero.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zero.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer>{
	@Query("select r from Review r where r.id = ?1")
	Review findById(int id);
	
	@Query("select r from Review r where r.progress.game.id = ?1 AND r.text IS NOT NULL")
	Collection<Review> findByGame(int gameId);
	
	@Query( value ="select r from Review r where r.progress.game.id = :gameId AND r.text IS NOT NULL")
	Page<Review> findByGamePage(@Param("gameId") int gameId, Pageable pageable);
	
	@Query("select r from Review r where r.id = (select max(r2.id) from Review r2 where r2.progress.user.username = ?1)")
	Review findLastReview(String username);
}
