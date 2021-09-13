package com.java17.core;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new UserHandlerMethodArgumentResolver());
    }
}
