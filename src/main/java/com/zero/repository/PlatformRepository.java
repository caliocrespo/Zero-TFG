package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zero.domain.Platform;

@Repository
public interface PlatformRepository extends JpaRepository<Platform,Integer>{

}
