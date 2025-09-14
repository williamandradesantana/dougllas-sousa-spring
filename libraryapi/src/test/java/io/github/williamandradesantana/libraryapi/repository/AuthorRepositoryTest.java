package io.github.williamandradesantana.libraryapi.repository;

import io.github.williamandradesantana.libraryapi.model.Author;
import io.github.williamandradesantana.libraryapi.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

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
}
