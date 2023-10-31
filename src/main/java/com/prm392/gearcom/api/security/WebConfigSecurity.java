package com.prm392.gearcom.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class WebConfigSecurity {

    private final JWTRequestFilter jwtRequestFilter;

    public WebConfigSecurity(JWTRequestFilter _jwtRequestFilter) {
        this.jwtRequestFilter = _jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtRequestFilter, AuthorizationFilter.class)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(
                                        "/product",
                                        "/product/c",
                                        "/category",
                                        "/category/*",
                                        "/api/v1/auth/register",
                                        "/api/v1/auth/login").permitAll()
                                .anyRequest().authenticated());

        return http.build();
    }
}
