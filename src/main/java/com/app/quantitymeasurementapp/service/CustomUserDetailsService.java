package com.app.quantitymeasurementapp.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurementapp.entity.AuthEntity;
import com.app.quantitymeasurementapp.exception.UserNotFoundException;
import com.app.quantitymeasurementapp.repository.AuthRepository;

@Service
public class CustomUserDetailsService {
	
	private AuthRepository userRepository;
	
	public CustomUserDetailsService(AuthRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	public UserDetails loadUserByUsername(String email){
        AuthEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with username: " + email));
        
        return User.builder()
        		   .username(user.getEmail())
        		   .password(user.getPassword())
        		   .roles(user.getRole())
        		   .build();
    } 
}