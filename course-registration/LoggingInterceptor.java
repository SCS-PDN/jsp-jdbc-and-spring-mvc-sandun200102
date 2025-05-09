package com.university.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        String email = request.getParameter("email");

        if (uri.contains("/login")) {
            System.out.println("Login attempt: " + email);
        }
        if (uri.contains("/register")) {
            System.out.println("Course registration attempt: " + uri);
        }

        return true;
    }
}