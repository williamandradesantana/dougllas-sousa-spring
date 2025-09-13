package io.github.williamandradesantana.libraryapi.repositories;

import io.github.williamandradesantana.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
