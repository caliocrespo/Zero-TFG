package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zero.domain.Progress;

@Repository
public interface ProgressRepository extends JpaRepository<Progress,Integer>{

}
