package com.projectboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 보호 기능 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                // CORS 보호 기능 비활성화
                .cors(AbstractHttpConfigurer::disable)
                // 모든 요청에 대해 접근 허용 (인증 절차 없음)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                // 기본 로그인 경로 설정
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll
                );
        return http.build();
    }
}
