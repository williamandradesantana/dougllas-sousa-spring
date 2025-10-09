package io.github.williamandradesantana.libraryapi.validator;

import io.github.williamandradesantana.libraryapi.exceptions.DuplicateRegisterException;
import io.github.williamandradesantana.libraryapi.exceptions.InvalidFieldException;
import io.github.williamandradesantana.libraryapi.model.Book;
import io.github.williamandradesantana.libraryapi.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private static final int YEAR_OF_PRICE_DEMAND = 2020;
    private final BookRepository bookRepository;

    public void validate(Book book) {
        if (existsRegisteredIsbn(book)) throw new DuplicateRegisterException("This ISBN already exists in DB!");
        if (priceRequiredIsNull(book)) {
            throw new InvalidFieldException("price", "For book with date previous 2020 the price is required!");
        }
    }

    private boolean priceRequiredIsNull(Book book) {
        return book.getPrice() == null && book.getPublishDate().getYear() >= YEAR_OF_PRICE_DEMAND;
    }

    private boolean existsRegisteredIsbn(Book book) {
        Optional<Book> bookFound = bookRepository.findByIsbn(book.getIsbn());
        if (book.getId() == null) {
            return bookFound.isPresent();
        }

        return bookFound.map(Book::getId).stream().anyMatch(id -> id.equals(book.getId()));
    }
}
