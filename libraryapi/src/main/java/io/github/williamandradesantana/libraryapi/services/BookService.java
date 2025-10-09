package io.github.williamandradesantana.libraryapi.services;

import io.github.williamandradesantana.libraryapi.model.Book;
import io.github.williamandradesantana.libraryapi.model.enums.Gender;
import io.github.williamandradesantana.libraryapi.repositories.BookRepository;
import io.github.williamandradesantana.libraryapi.validator.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.williamandradesantana.libraryapi.repositories.specs.BookSpecs.*;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookValidator bookValidator;

    public Book create(Book book) {
        bookValidator.validate(book);
        return bookRepository.save(book);
    }


    public Page<Book> searchBySpecification(
            String isbn,
            String title,
            String authorName,
            Gender gender,
            Integer yearOfPublication,
            Integer page,
            Integer pageSize
    ) {
        Specification<Book> specs = Specification.where(
                (root, query, cb) -> cb.conjunction()
        );

        if (isbn != null) specs = specs.and(isbnEqual(isbn));
        if (title != null) specs = specs.and(titleEqual(title));
        if (gender != null) specs = specs.and(genderEqual(gender));
        if (yearOfPublication != null) specs = specs.and(yearOfPublicationEqual(yearOfPublication));
        if (authorName != null) specs = specs.and(authorNameLike(authorName));

        Pageable pageableRequest = PageRequest.of(page, pageSize, Sort.by("title").ascending());
        return bookRepository.findAll(specs, pageableRequest);
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
        bookValidator.validate(entity);
        bookRepository.save(entity);
    }
}
