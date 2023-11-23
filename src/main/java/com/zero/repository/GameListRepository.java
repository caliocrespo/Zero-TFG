package com.zero.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zero.domain.GameList;

@Repository
public interface GameListRepository extends JpaRepository<GameList,Integer>{
	
	@Query("select g from GameList g where g.id = ?1")
	GameList findById(int id);
	
	@Query("select g from GameList g where g.user.username = ?1")
	Page<GameList> findByUsernamePage(String username, Pageable pageable);
	
	@Query("select g from GameList g where g.user.username = ?1")
	Collection<GameList> findByUsername(String username);
	
	@Query("select g from GameList g where g.user.username = ?1 AND shared = TRUE")
	Page<GameList> findByUsernamePublic(String username, Pageable pageable);
}
