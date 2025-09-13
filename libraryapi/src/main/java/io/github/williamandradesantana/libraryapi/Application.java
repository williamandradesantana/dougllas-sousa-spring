package io.github.williamandradesantana.libraryapi;

import io.github.williamandradesantana.libraryapi.model.Author;
import io.github.williamandradesantana.libraryapi.repositories.AuthorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
        var context = SpringApplication.run(Application.class, args);
        var repository = context.getBean(AuthorRepository.class);
//        exampleSaveRegister(repository);
        quantityAuthorsInDatabase(repository);
        howManyAuthorsStartsWithTheLetter(repository, "M");
    }

    public static void exampleSaveRegister(AuthorRepository authorRepository) {
        var author = new Author();

        author.setName("Morgan");
        author.setNationality("American");
        author.setDateOfBirth(LocalDate.of(1990, 8, 15));

        var authorSaved = authorRepository.save(author);
        System.out.println("Author with id: " + authorSaved.getId());
    }

    public static void quantityAuthorsInDatabase(AuthorRepository authorRepository) {
        System.out.println(authorRepository.count());
    }

    public static void howManyAuthorsStartsWithTheLetter(AuthorRepository authorRepository, String letter) {
//        authorRepository.howManyAuthorsStartsWithTheLetter(letter).forEach(System.out::println);

        authorRepository.findByNameStartsWithIgnoreCase(letter).forEach(System.out::println);
    }
}
