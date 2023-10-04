package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zero.domain.Genre;


public interface GenreRepository extends JpaRepository<Genre,Integer>{

}
