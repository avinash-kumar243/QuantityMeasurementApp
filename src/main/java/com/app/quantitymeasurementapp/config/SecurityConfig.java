package com.app.quantitymeasurementapp.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.app.quantitymeasurementapp.service.OAuth2AuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {  
	
//	This class is the main Spring Security setup class.
	
//	It tells our application:
//
//		which URLs are public
//		which URLs need JWT token
//		how to handle unauthorized access
//		where to run our JWTFilter
//		how CORS should work
//		session should be stateless
	
	
	private final JWTFilter filter;
	private final AuthenticationEntryPoint point; 
	private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
		 	.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authz -> authz
					.requestMatchers("/auth/**").permitAll()
					.requestMatchers("/oauth2/**", "/login/**").permitAll()
					.requestMatchers("/h2-console/**").permitAll()
	                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
	                .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
					.anyRequest().authenticated()
			)
			.oauth2Login(oauth -> oauth
			        .successHandler(oAuth2AuthenticationSuccessHandler)
			        .failureUrl("http://localhost:4200/auth?oauth2=failed")
			)
			.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.exceptionHandling(ex -> ex.authenticationEntryPoint(point));
			
		 
			http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
			http.headers(headers-> headers.frameOptions(frame-> frame.disable()));
		
		return http.build();
	}
	
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {	
		CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.setAllowedOrigins(Arrays.asList(
			    "http://localhost:3000",
			    "http://localhost:8080",
			    "http://127.0.0.1:5500",
			    "http://localhost:4200",
			    "https://quantitymeasurementapp-production-6c3d.up.railway.app",
			    "https://quantity-measurement-app-frontend-liard.vercel.app",
			    "https://quantity-measurement-app-frontend-ah4ly7xig.vercel.app"
		));
		
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source; 
	}
}