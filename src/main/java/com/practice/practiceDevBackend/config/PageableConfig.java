package com.practice.practiceDevBackend.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
public class PageableConfig implements PageableHandlerMethodArgumentResolverCustomizer {

    @Override
    public void customize(org.springframework.data.web.PageableHandlerMethodArgumentResolver resolver) {
        resolver.setMaxPageSize(12);
    }
}