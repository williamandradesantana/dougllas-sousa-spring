package io.github.williamandradesantana.libraryapi.services;

import io.github.williamandradesantana.libraryapi.repositories.AuthorRepository;
import io.github.williamandradesantana.libraryapi.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

}
