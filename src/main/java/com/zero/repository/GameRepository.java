package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zero.domain.Game;

@Repository
public interface GameRepository extends JpaRepository<Game,Integer>{

}
