package com.app.microservices.auth_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.microservices.auth_service.entity.AuthEntity;


@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long> {    
   
	Optional<AuthEntity> findByEmail(String username);
	boolean existsByEmail(String email);
}