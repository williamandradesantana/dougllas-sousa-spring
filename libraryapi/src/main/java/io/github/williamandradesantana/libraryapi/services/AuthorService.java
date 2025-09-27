package io.github.williamandradesantana.libraryapi.services;

import io.github.williamandradesantana.libraryapi.controller.dto.AuthorDTO;
import io.github.williamandradesantana.libraryapi.model.Author;
import io.github.williamandradesantana.libraryapi.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAll(String name, String nationality) {
        if (name != null && nationality != null) {
            return authorRepository.findByNameContainingIgnoreCaseAndNationalityContainingIgnoreCase(name, nationality);
        }
        else if (name != null) {
           return authorRepository.findByNameContainingIgnoreCase(name);
        }
        else if (nationality != null) {
            return authorRepository.findByNationalityContainingIgnoreCase(nationality);
        }
        return authorRepository.findAll();
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> get(UUID id) {
        return authorRepository.findById(id);
    }

    public void delete(Author author) {
        authorRepository.delete(author);
    }

    public void update(Author author) {
        if (author.getId() == null) {
            throw new IllegalArgumentException("This Author no exists in DB!");
        }
        authorRepository.save(author);
    }
}
