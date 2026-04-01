package com.app.quantitymeasurementapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurementapp.dto.LoginRequestDTO;
import com.app.quantitymeasurementapp.dto.ResponseDTO;
import com.app.quantitymeasurementapp.dto.SignupRequestDTO;
import com.app.quantitymeasurementapp.entity.AuthEntity;
import com.app.quantitymeasurementapp.exception.UserNotFoundException;
import com.app.quantitymeasurementapp.repository.AuthRepository;

@Service
public class AuthService {
	private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService service;

    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder, JWTService service) {
        this.authRepository = authRepository; 
        this.passwordEncoder = passwordEncoder;
        this.service = service;
    }
        
    
    public ResponseDTO loginUser(LoginRequestDTO dto) {
    	// Extract auth user
		AuthEntity entitydb = authRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new UserNotFoundException("User not found! please signup!!!"));
		
		// Password matches
		if(!passwordEncoder.matches(dto.getPassword(), entitydb.getPassword())) {
			throw new RuntimeException("Wrong password!!!");
		}
		
		// Generate token
		String token = service.generateToken(dto.getEmail());
		
		// Return Result dto to authController
		return new ResponseDTO(token, "Login successful"); 
    }

    
    public ResponseDTO registerUser(SignupRequestDTO dto) {
        if(authRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("User already exists with this email!!!");
        }

        AuthEntity entitydb = new AuthEntity(); 
        entitydb.setEmail(dto.getEmail()); 
        entitydb.setPassword(passwordEncoder.encode(dto.getPassword()));
        entitydb.setUsername(dto.getUsername());
        entitydb.setRole("USER");
        
        authRepository.save(entitydb); 
        
        String token = service.generateToken(entitydb.getEmail());
        
        return new ResponseDTO(token, "Sign up successful");
    }
}