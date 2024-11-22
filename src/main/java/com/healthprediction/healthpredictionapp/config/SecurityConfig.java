package com.healthprediction.healthpredictionapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disable CSRF for simplicity (not recommended for production)
            .authorizeRequests()
                .requestMatchers("/api/users/register", "/api/users/login").permitAll()  // Allow public access
                .anyRequest().authenticated()  // Restrict all other endpoints
            .and()
            .httpBasic();  // Use basic authentication for other endpoints

        return http.build();
    }
}


