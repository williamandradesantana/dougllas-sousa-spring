package io.github.williamandradesantana.libraryapi.repository;

import io.github.williamandradesantana.libraryapi.model.Author;
import io.github.williamandradesantana.libraryapi.model.Book;
import io.github.williamandradesantana.libraryapi.model.enums.Gender;
import io.github.williamandradesantana.libraryapi.repositories.AuthorRepository;
import io.github.williamandradesantana.libraryapi.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void saveBook() {

        var book = new Book();
        book.setIsbn("980");
        book.setPrice(BigDecimal.valueOf(100.9));
        book.setGender(Gender.BIOGRAPHY);
        book.setTitle("UFO");
        book.setPublishDate(LocalDate.of(1923, 1, 1));

        Author author = authorRepository
                .findById(UUID.fromString("e5968a98-624f-4a1e-8d0f-b6133cd0c89f"))
                .orElse(null);

        book.setAuthor(author);
        bookRepository.save(book);
    }

    @Test
    void saveAuthorAndBookTest() {

        var book = new Book();
        book.setIsbn("9s2231232032");
        book.setPrice(BigDecimal.valueOf(200.9));
        book.setGender(Gender.ROMANCE);
        book.setTitle("Animal Farm");
        book.setPublishDate(LocalDate.of(1923, 1, 1));

        var author = new Author();
        author.setName("George Orwell");
        author.setNationality("British");
        author.setDateOfBirth(LocalDate.of(1903, 6, 25));

        var authorSaved = authorRepository.save(author);

        book.setAuthor(authorSaved);
        bookRepository.save(book);
    }

    @Test
    void operationsWithCascade() {

        var book = new Book();
        book.setIsbn("980");
        book.setPrice(BigDecimal.valueOf(100.9));
        book.setGender(Gender.BIOGRAPHY);
        book.setTitle("Another Book");
        book.setPublishDate(LocalDate.of(1923, 1, 1));

        var author = new Author();
        author.setName("Plato");
        author.setNationality("Grecian");
        author.setDateOfBirth(LocalDate.of(-428, 8, 15));

        book.setAuthor(author);
        bookRepository.save(book);
    }

    @Test
    void updateBookAuthor(){
        var bookId = UUID.fromString("377f75b8-3d82-415d-8d71-65a1095fcc59");
        var book = bookRepository
                .findById(bookId)
                .orElse(null);

        var authorId = UUID.fromString("281e49cc-edc9-4da2-9285-1b51465dccb9");
        var author = authorRepository
                .findById(authorId)
                .orElse(null);

        book.setTitle("Republic");
        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Test
    void delete() {
        var id = UUID.fromString("377f75b8-3d82-415d-8d71-65a1095fcc59");
        bookRepository.deleteById(id);
    }

    @Test
    @Transactional
    void searchBookTest() {
        var id = UUID.fromString("94176239-f556-46ae-9041-0825bb7f9cd7");
        var book = bookRepository.findById(id).orElse(null);
        System.out.println(book.getTitle());
        System.out.println(book.getAuthor().getName());
    }

    @Test
    void searchBookTitleTest() {
        var title = "UFO";
        bookRepository.findByTitle(title).forEach(System.out::println);
    }

    @Test
    void searchBookIsbnTest() {
        var isbn = "980";
        bookRepository.findByIsbn(isbn).forEach(System.out::println);
    }

    @Test
    void searchBookTitleAndPriceTest(){
        var price = BigDecimal.valueOf(100.9);
        var title = "UFO";
        bookRepository.findByTitleAndPrice(title, price).forEach(System.out::println);
    }

    @Test
    void searchBookTitleOrIsbnTest(){
        var isbn = "980";
        var title = "UFO";
        bookRepository.findByTitleOrIsbn(title, isbn).forEach(System.out::println);
    }

    @Test
    void searchBookPublishDateBetweenTest(){
        var start = LocalDate.of(2000, 1, 1);
        bookRepository.findByPublishDateBetween(start, LocalDate.now()).forEach(System.out::println);
    }
}
