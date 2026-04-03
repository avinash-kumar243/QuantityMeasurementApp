package com.app.microservices.auth_service.service;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys; 


@Component
public class JWTService {  // Perform JWT Token Related operations   // JWTService = generate JWT token and perform validation logic
	
	// take value of "jwt.secret" from application.yml and put in "secret"
	@Value("${jwt.secret}")
	private String secret;
	
	// take value of "jwt.expiration" from application.yml and put in "jwtTokenValidaity"
	@Value("${jwt.expiration}")
	private long jwtTokenValidaity;

	// This method converts your secret string into a real JWT signing key.
    private SecretKey getSigningKey() {
    	byte []keyBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes); 
    }
    

    public String generateToken(String email) {
        return Jwts.builder() 
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtTokenValidaity))
                .signWith(getSigningKey())
                .compact(); 
    }

    public String extractEmailFromToken(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpirationDate(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractEmailFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey()) 
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
	
}