package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zero.domain.GameList;

@Repository
public interface GameListRepository extends JpaRepository<GameList,Integer>{

}
