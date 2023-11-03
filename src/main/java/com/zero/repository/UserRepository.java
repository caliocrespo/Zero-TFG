package com.zero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zero.domain.UserEntity;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{
	
	@Query("select u from UserEntity u where u.username= ?1")
	UserEntity findByUsername(String username);
	
	@Query("select u from UserEntity u where u.email = ?1")
	UserEntity findByEmail(String email);
}
