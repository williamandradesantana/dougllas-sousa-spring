package io.github.williamandradesantana.libraryapi.services;

import io.github.williamandradesantana.libraryapi.model.User;
import io.github.williamandradesantana.libraryapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServices {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void create(User user) {
        var password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
