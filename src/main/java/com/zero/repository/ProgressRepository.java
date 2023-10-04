package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zero.domain.Progress;


public interface ProgressRepository extends JpaRepository<Progress,Integer>{

}
