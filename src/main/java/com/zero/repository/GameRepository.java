package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zero.domain.Game;


public interface GameRepository extends JpaRepository<Game,Integer>{

}
