package com.zero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zero.domain.UserEntity;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{
	
	Optional<UserEntity> findByUsername (String username);
}
