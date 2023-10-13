package com.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zero.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
