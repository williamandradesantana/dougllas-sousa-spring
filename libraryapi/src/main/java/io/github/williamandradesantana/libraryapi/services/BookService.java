package io.github.williamandradesantana.libraryapi.services;

import io.github.williamandradesantana.libraryapi.model.Book;
import io.github.williamandradesantana.libraryapi.model.enums.Gender;
import io.github.williamandradesantana.libraryapi.repositories.AuthorRepository;
import io.github.williamandradesantana.libraryapi.repositories.BookRepository;
import io.github.williamandradesantana.libraryapi.repositories.specs.BookSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static io.github.williamandradesantana.libraryapi.repositories.specs.BookSpecs.*;
import java.util.List;
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


    public List<Book> searchBySpecification(
            String isbn, String title, String authorName, Gender gender, Integer yearOfPublication
    ) {
//        Specification<Book> specs = Specification.where(
//                BookSpecs.genderEqual(gender)
//                .and(BookSpecs.isbnEqual(isbn))
//                .and(BookSpecs.titleEqual(title))
//        );
        Specification<Book> specs = Specification.where(
                (root, query, cb) -> cb.conjunction()
        );

        if (isbn != null) specs = specs.and(isbnEqual(isbn));
        if (title != null) specs = specs.and(titleEqual(title));
        if (gender != null) specs = specs.and(genderEqual(gender));
        if (yearOfPublication != null) specs = specs.and(yearOfPublicationEqual(yearOfPublication));
        if (authorName != null) specs = specs.and(authorNameLike(authorName));

        return bookRepository.findAll(specs);
    }

    public Optional<Book> get(UUID bookId) {
        return bookRepository.findById(bookId);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public void update(Book entity) {
        if (entity.getId() == null) {
            throw new IllegalArgumentException("This book no exists in DB!");
        }
        bookRepository.save(entity);
    }
}
