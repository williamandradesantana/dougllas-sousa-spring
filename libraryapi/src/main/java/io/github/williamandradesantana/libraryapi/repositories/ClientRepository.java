package io.github.williamandradesantana.libraryapi.repositories;

import io.github.williamandradesantana.libraryapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByClientId(String clientId);
}
