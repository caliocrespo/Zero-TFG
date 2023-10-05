package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zero.domain.List;

@Repository
public interface ListRepository extends JpaRepository<List,Integer>{

}
