package com.friend.friend.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(withDefaults());
        http.authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers( "/","/swagger-ui/**", "/v3/api-docs/**","/hello","/login","/api/v1/**").permitAll()
                                .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
