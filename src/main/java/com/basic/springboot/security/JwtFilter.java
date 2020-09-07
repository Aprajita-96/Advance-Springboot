package com.basic.springboot.security;

import lombok.Data;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
public class JwtFilter implements Filter {
    private JwtUtil jwtUtil;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        final String authHeader=request.getHeader("ServiceAuth");

        if(authHeader==null || !authHeader.startsWith("Bearer")){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        final String token=authHeader.substring(7);
        boolean isvalidtoken=jwtUtil.validateToken(token);
        if(!isvalidtoken){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"invalid token");
            return;
        }
        filterChain.doFilter(request,response);
    }
}
