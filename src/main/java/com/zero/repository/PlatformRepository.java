package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zero.domain.Platform;

@Repository
public interface PlatformRepository extends JpaRepository<Platform,Integer>{

	@Query("select p from Platform p where p.name = ?1")
	Platform findByName(String namePlatform);
	
	@Query("select p from Platform p where p.idRAWG = ?1")
	Platform findByRAWGId (int idRAWG);
	
	@Query("select p from Platform p where p.id = ?1")
	Platform findById(int id);
}
