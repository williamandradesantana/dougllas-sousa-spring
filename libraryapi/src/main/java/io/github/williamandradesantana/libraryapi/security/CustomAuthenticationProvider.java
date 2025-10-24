package io.github.williamandradesantana.libraryapi.security;

import io.github.williamandradesantana.libraryapi.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserServices userServices;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        var userFound = userServices.getByLogin(login);
        if (userFound == null) {
            throw getIncorrectLoginOrPassword();
        }

        var matchesPassword = passwordEncoder.matches(password, userFound.getPassword());

        if (matchesPassword) {
            return new CustomAuthentication(userFound);
        }

        throw getIncorrectLoginOrPassword();
    }

    private UsernameNotFoundException getIncorrectLoginOrPassword() {
        return new UsernameNotFoundException("Incorrect login or password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}