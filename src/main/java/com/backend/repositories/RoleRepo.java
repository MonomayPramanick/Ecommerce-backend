package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Role;



public interface RoleRepo extends JpaRepository<Role, Integer>{

	
}

