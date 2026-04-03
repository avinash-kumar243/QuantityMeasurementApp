package com.app.microservices.auth_service.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.app.microservices.auth_service.entity.AuthEntity;
import com.app.microservices.auth_service.entity.ProviderType;
import com.app.microservices.auth_service.repository.AuthRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/*
 * 	After Google login succeeds:
		get logged-in Google user
		extract email, name, maybe Google user id
		check DB:
		if user exists → use that user
		if not exists → create new user
		generate JWT using your existing JWTService
		send token back to frontend

	So this class connects:
		Google login → your JWT system

	Without this class, OAuth2 login may succeed, but it won’t integrate nicely with your current JWT-protected REST APIs.
 */


@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	private final AuthRepository authRepository;
	private final JWTService jwtService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		
		Map<String, Object> attributes = oAuth2User.getAttributes();

		String email = (String) attributes.get("email");
		String name = (String) attributes.get("name");
		String providerId = (String) attributes.get("sub");   // Google unique id

		if(email == null || email.isBlank()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email not found from OAuth2 provider");
			return;
		}

		Optional<AuthEntity> optionalUser = authRepository.findByEmail(email);
		AuthEntity user;

		if(optionalUser.isPresent()) {
			
			user = optionalUser.get();

			// update Google info if needed
			if(user.getProviderType() == null) {
				user.setProviderType(ProviderType.GOOGLE);
			}
			if(user.getProviderId() == null || user.getProviderId().isBlank()) {
				user.setProviderId(providerId);
			}
			if(user.getUsername() == null || user.getUsername().isBlank()) {
				user.setUsername(name);
			}

			authRepository.save(user);
		} else {
			user = new AuthEntity();
			user.setEmail(email);
			user.setUsername(name);
			user.setPassword("");          // no local password for Google login
			user.setRole("USER");
			user.setProviderType(ProviderType.GOOGLE);
			user.setProviderId(providerId);

			authRepository.save(user);
		}

		String token = jwtService.generateToken(email);

		String redirectUrl = "http://localhost:4200/auth?token=" + URLEncoder.encode(token, StandardCharsets.UTF_8) + "&oauth2=success";

		response.sendRedirect(redirectUrl);
	}
}