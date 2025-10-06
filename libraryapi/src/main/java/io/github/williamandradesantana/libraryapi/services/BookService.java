package io.github.williamandradesantana.libraryapi.services;

import io.github.williamandradesantana.libraryapi.controller.dto.SearchBookDTO;
import io.github.williamandradesantana.libraryapi.model.Book;
import io.github.williamandradesantana.libraryapi.repositories.AuthorRepository;
import io.github.williamandradesantana.libraryapi.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> get(UUID bookId) {
        return bookRepository.findById(bookId);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }
}
