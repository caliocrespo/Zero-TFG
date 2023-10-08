package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zero.domain.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Integer>{
	
	@Query("select d from Developer d where d.slug = ?1")
	Developer findBySlug (String slugDeveloper);
}
