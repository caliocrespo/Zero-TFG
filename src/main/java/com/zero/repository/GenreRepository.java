package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zero.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Integer>{
	@Query("select g from Genre g where g.slug = ?1")
	Genre findByGenreSlug(String genreSlug);
}
