package com.zero.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.zero.domain.Game;
import com.zero.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Integer>{
	@Query("select g from Genre g where g.slug = ?1")
	Genre findByGenreSlug(String genreSlug);
	
	@Query("select g from Genre g where g.id = ?1")
	Genre findById(int id);
	
	@Query("select g from Genre g")
	List<Genre> findAll();
	
	Page<Genre> findAll(Pageable pageable);
	
	@Query(value = "select g.games from Genre g where g.id = :genreId")
	Page<Game> findByGenrePage(@Param("genreId") int genreId, Pageable pageable);
}
