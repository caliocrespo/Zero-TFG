package com.zero.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zero.domain.Progress;

@Repository
public interface ProgressRepository extends JpaRepository<Progress,Integer>{
	@Query("select p from Progress p where p.game.id = ?1")
	Collection<Progress> findByGame(int gameId);
	
	@Query("select AVG(p.rating) as rating from Progress p where p.game.id = ?1")
	double findRatingByGame(int gameId); 
	
	@Query("select p from Progress p where p.game.id = ?1 AND p.user.username = ?2")
	Progress findByGameAndUser(int gameId, String username);
	
	@Query("select p from Progress p where p.id = ?1")
	Progress findById(int progressId);
}
