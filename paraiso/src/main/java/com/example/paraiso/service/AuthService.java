package com.example.paraiso.service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean isAdmin(Authentication auth) {
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    public boolean isSameUser(String email) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && email.equals(auth.getName());
    }

    public boolean isAdminOrSameUser(String email) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return isAdmin(auth) || isSameUser(email);
    }
}
