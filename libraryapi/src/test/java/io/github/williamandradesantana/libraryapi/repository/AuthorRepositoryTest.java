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
import java.util.*;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void saveTest(){
        var author = new Author();

        author.setName("James Clear");
        author.setNationality("American");
        author.setDateOfBirth(LocalDate.of(1990, 8, 15));

        var authorSaved = authorRepository.save(author);
        System.out.println("Author with id: " + authorSaved.getId());
    }

    @Test
    public void quantityAuthorsInDatabase() {
        System.out.println(authorRepository.count());
    }

    @Test
    public void howManyAuthorsStartsWithTheLetter() {
//        authorRepository.howManyAuthorsStartsWithTheLetter(letter).forEach(System.out::println);

        authorRepository.findByNameStartsWithIgnoreCase("J").forEach(System.out::println);
    }

    @Test
    public void updateAuthor() {
        var id = UUID.fromString("7bd3bf2a-b04a-441e-9382-80e3931450c7");
        Optional<Author> possibleAuthor = authorRepository.findById(id);

        if (possibleAuthor.isPresent()) {
            var foundAuthor = possibleAuthor.get();
            System.out.println("Data: ");
            System.out.println(foundAuthor);

            foundAuthor.setNationality("Brazilian");
            authorRepository.save(foundAuthor);
        }
    }

    @Test
    public void findAll() {
        authorRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void deleteByIdTest() {
        authorRepository.deleteById(UUID.fromString("7bd3bf2a-b04a-441e-9382-80e3931450c7"));
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("b8cdf950-b4f1-42f5-a59f-56781dff975c");
        var james = authorRepository.getReferenceById(id);
        authorRepository.delete(james);
    }

    @Test
    void saveAuthorWithBooksTest(){
        var author = new Author();

        author.setName("Robert Martin");
        author.setNationality("American");
        author.setDateOfBirth(LocalDate.of(1952, 12, 5));

        var cleanCode = new Book();
        cleanCode.setIsbn("0132350882");
        cleanCode.setPrice(BigDecimal.valueOf(27.99));
        cleanCode.setGender(Gender.SCIENCE);
        cleanCode.setTitle("Clean Code");
        cleanCode.setPublishDate(LocalDate.of(2008, 8, 1));
        cleanCode.setAuthor(author);

        var cleanArchitecture = new Book();
        cleanArchitecture.setIsbn("978-0134494166");
        cleanArchitecture.setPrice(BigDecimal.valueOf(19.56));
        cleanArchitecture.setGender(Gender.SCIENCE);
        cleanArchitecture.setTitle("Clean Architecture");
        cleanArchitecture.setPublishDate(LocalDate.of(2017, 9, 10));
        cleanArchitecture.setAuthor(author);

        List<Book> books = new ArrayList<>(Arrays.asList(cleanCode, cleanArchitecture));
        author.setBooks(books);

        authorRepository.save(author); // utilizando o cascade não precisa usar o saveAll()
//        bookRepository.saveAll(author.getBooks());
    }

    @Test
    void authorsBooksWithLazyTest() {
        var id = UUID.fromString("a6bd6d32-bc01-4f6d-bb59-6879b53c0178");
        var author = authorRepository.findById(id).get();

//        Buscar livros do autor
        var books = bookRepository.findByAuthor(author);
        author.setBooks(books);
        author.getBooks().forEach(System.out::println);
    }
    @Test
    void authorsBooksWithEagerTest() {
        authorRepository
                .findById(UUID.fromString("a6bd6d32-bc01-4f6d-bb59-6879b53c0178"))
                .ifPresent(a -> {
                    a.getBooks().forEach(System.out::println);
                });
    }
}
