package io.github.williamandradesantana.libraryapi.repositories;

import io.github.williamandradesantana.libraryapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByLogin(String login);
}
