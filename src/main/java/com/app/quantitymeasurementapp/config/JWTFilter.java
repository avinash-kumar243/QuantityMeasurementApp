package com.app.quantitymeasurementapp.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.quantitymeasurementapp.service.CustomUserDetailsService;
import com.app.quantitymeasurementapp.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {  // JWTFilter = It ask JWT token (from JWTSevice) for every request, and authenticate user
	
	private CustomUserDetailsService customUserDetailsService;
	private JWTService jwtService;
	
	// Constructor Injection
	public JWTFilter(CustomUserDetailsService customUserDetailsService, JWTService jwtService) {
		this.customUserDetailsService = customUserDetailsService;
		this.jwtService = jwtService;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
        String token = null;
        String email = null;
        
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);

            try {
            	email = jwtService.extractEmailFromToken(token);
            } catch (Exception e) {
                System.out.println("Invalid JWT token: " + e.getMessage());
            }
        }
        
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = customUserDetailsService.loadUserByUsername(email);

            if(jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        filterChain.doFilter(request, response);
    }
	
}