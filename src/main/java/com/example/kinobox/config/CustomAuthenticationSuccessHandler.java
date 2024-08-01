package com.example.kinobox.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        boolean hasUserRole = authentication.getAuthorities().stream().anyMatch(r->r.getAuthority().equals("ROLE_USER"));
        boolean hasAdminRole = authentication.getAuthorities().stream().anyMatch(r->r.getAuthority().equals("ROLE_ADMIN"));
        if(hasUserRole || hasAdminRole){
            response.sendRedirect("/");
        }
    }
}
