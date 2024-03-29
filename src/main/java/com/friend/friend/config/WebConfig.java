package com.friend.friend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://13.209.145.28:8080",
                        "https://43.200.230.191:443",
                        "https://dani-5--rococo-dragon-c69f42.netlify.app",
                        "http://localhost:3000",
                        "https://localhost:3000",
                        "https://127.0.0.1:3000",
                        "https://dev--rococo-dragon-c69f42.netlify.app/"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE","PATCH")
        ;

    }
}
