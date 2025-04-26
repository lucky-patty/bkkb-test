package com.test.bkk.backend.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppConfig {
    @Bean
    public JdbcTemplate jbdcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {        
        // Disable security for dev
        http.csrf((csrf) -> csrf.disable())
        .authorizeHttpRequests((auth) -> auth.anyRequest().permitAll())
        .formLogin(login -> login.disable())
        .httpBasic(basic -> basic.disable());
        return http.build();
    }
}
