package io.github.williamandradesantana.libraryapi.security;

import io.github.williamandradesantana.libraryapi.model.User;
import io.github.williamandradesantana.libraryapi.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityService {

    private final UserServices userServices;

    public User getUserByLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof CustomAuthentication customAuthentication) return customAuthentication.getUser();
        return null;
    }
}
