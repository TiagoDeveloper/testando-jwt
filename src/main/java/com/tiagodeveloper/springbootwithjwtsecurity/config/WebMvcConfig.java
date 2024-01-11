package com.tiagodeveloper.springbootwithjwtsecurity.config;

import com.tiagodeveloper.springbootwithjwtsecurity.interceptors.HomeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private HomeInterceptor homeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(homeInterceptor).addPathPatterns("/home/**");
    }
}
