package io.github.williamandradesantana.libraryapi.services;

import io.github.williamandradesantana.libraryapi.model.Author;
import io.github.williamandradesantana.libraryapi.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }
}
