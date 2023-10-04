package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zero.domain.User;


public interface UserRepository extends JpaRepository<User,Integer>{

}
