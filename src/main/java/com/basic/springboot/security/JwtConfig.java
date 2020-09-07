package com.basic.springboot.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public FilterRegistrationBean<JwtFilter> filterFilterRegistrationBean(){
        FilterRegistrationBean<JwtFilter> registrationBean=new FilterRegistrationBean<JwtFilter>();
        JwtFilter jwtFilter=new JwtFilter();
        jwtFilter.setJwtUtil(jwtUtil);
        registrationBean.setFilter(jwtFilter);
         //adding url mappings where jwt will be applied

        registrationBean.addUrlPatterns(
                "/login/*",
                "/profile/*"
        );
        registrationBean.setOrder(2);
        return registrationBean;
    }

}
