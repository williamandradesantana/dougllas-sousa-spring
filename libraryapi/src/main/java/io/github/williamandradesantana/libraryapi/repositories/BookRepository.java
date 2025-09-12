package io.github.williamandradesantana.libraryapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<BookRepository, UUID> {
}
