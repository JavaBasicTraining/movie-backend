package com.example.movie_backend.config;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Component
public class AccessDecisionService {

    private final RequestMatcher adminRequestMatcher = new AntPathRequestMatcher("/admin/**");

    public boolean isUserAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            Collection<?> authorities = ((UserDetails) principal).getAuthorities();
            return authorities.stream().anyMatch(a -> "ROLE_ADMIN".equals(((GrantedAuthority) a).getAuthority()));
        }
        return false;
    }

    public boolean isAdminPath(HttpServletRequest request) {
        return adminRequestMatcher.matches(request);
    }
}
