package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zero.domain.Plataform;

@Repository
public interface PlataformRepository extends JpaRepository<Plataform,Integer>{

}
