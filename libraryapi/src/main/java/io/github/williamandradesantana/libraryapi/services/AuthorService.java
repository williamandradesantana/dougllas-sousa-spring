package io.github.williamandradesantana.libraryapi.services;

import io.github.williamandradesantana.libraryapi.exceptions.AuthorHaveABookException;
import io.github.williamandradesantana.libraryapi.model.Author;
import io.github.williamandradesantana.libraryapi.repositories.AuthorRepository;
import io.github.williamandradesantana.libraryapi.repositories.BookRepository;
import io.github.williamandradesantana.libraryapi.validator.AuthorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorValidator authorValidator;
    private final BookRepository bookRepository;

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

    public List<Author> searchByExample(String name, String nationality) {
        var author = new Author();
        author.setName(name);
        author.setNationality(nationality);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id", "dateOfBirth", "createdAt")
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Author> authorExample = Example.of(author, matcher);

        return authorRepository.findAll(authorExample);
    }

    public Author save(Author author) {
        authorValidator.validate(author);
        return authorRepository.save(author);
    }

    public Optional<Author> get(UUID id) {
        return authorRepository.findById(id);
    }

    public void delete(Author author) {
        if (hasABook(author)) {
            throw new AuthorHaveABookException("Cannot delete author because they have registered books.");
        }
        authorRepository.delete(author);
    }

    public void update(Author author) {
        if (author.getId() == null) {
            throw new IllegalArgumentException("This Author no exists in DB!");
        }
        authorValidator.validate(author);
        authorRepository.save(author);
    }

    public boolean hasABook(Author author) {
        return bookRepository.existsByAuthor(author);
    }
}
