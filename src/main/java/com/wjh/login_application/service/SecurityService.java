package com.wjh.login_application.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    public boolean isElevatedAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> {
                    String authString = grantedAuthority.getAuthority();
                    return authString.equalsIgnoreCase("ROLE_MANAGER")
                            || authString.equalsIgnoreCase("ROLE_ADMIN");
                });
    }

    public boolean isUserAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String);
    }
}
