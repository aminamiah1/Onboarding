package com.example.ase2022y203.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RequestRouter implements WebMvcConfigurer{
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/candidates").setViewName("candidate/all-candidates");
        registry.addViewController("/contact").setViewName("contact-us");
        registry.addViewController("/registration").setViewName("candidate/add");
        registry.addViewController("/successPage").setViewName("registration/successPage");
        registry.addViewController("/404").setViewName("error/404");
        registry.addViewController("/personal-info-form").setViewName("candidate/personal-info-form");
    }
}