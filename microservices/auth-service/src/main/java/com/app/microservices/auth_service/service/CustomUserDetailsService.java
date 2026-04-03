package com.app.microservices.auth_service.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.app.microservices.auth_service.entity.AuthEntity;
import com.app.microservices.auth_service.exception.UserNotFoundException;
import com.app.microservices.auth_service.repository.AuthRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private AuthRepository userRepository;
	
	public CustomUserDetailsService(AuthRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	public UserDetails loadUserByUsername(String email){
        AuthEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with username: " + email));
        
        String password = user.getPassword() == null ? "" : user.getPassword();
		String role = user.getRole() == null || user.getRole().isBlank() ? "USER" : user.getRole();
        
        return User.builder()
        		   .username(user.getEmail())
        		   .password(user.getPassword())
        		   .roles(user.getRole())
        		   .build();
    } 
}