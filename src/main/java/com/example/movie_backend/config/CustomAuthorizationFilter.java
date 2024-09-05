package com.example.movie_backend.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthorizationFilter extends HttpFilter {
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String path = request.getRequestURI();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (path.startsWith("/admin") && !hasRole(authentication, "ROLE_ADMIN")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have the required permission");
            return;
        } else if (path.startsWith("/user") && !hasRole(authentication, "ROLE_USER")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have the required permission");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean hasRole(Authentication authentication, String role) {
        return authentication != null && authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
    }
}
