package com.app.microservices.auth_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.microservices.auth_service.service.OAuth2AuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTFilter filter;
    private final AuthenticationEntryPoint point;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/oauth2/**", "/login/**").permitAll()
                    .requestMatchers("/actuator/**").permitAll()
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                    .anyRequest().authenticated()
            )
            .oauth2Login(oauth -> oauth
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    .failureUrl("http://localhost:4200/auth?oauth2=failed")
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(ex -> ex.authenticationEntryPoint(point));

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}