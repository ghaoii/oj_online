package com.ghaoi.oj_online.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).
                addPathPatterns("/problem/**").
                excludePathPatterns("/problem/insert").
                excludePathPatterns("/problem/delete").
                excludePathPatterns("/problem/search");
    }
}
