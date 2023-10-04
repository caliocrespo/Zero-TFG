package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zero.domain.Review;


public interface ReviewRepository extends JpaRepository<Review,Integer>{

}
