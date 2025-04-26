package com.test.bkk.backend.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
public class TestAppConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {        
        // Disable security for dev
        http.csrf((csrf) -> csrf.disable())
        .authorizeHttpRequests((auth) -> auth.anyRequest().permitAll());
        return http.build();
    }
}
