package io.github.williamandradesantana.libraryapi.services;

import io.github.williamandradesantana.libraryapi.model.Author;
import io.github.williamandradesantana.libraryapi.model.Book;
import io.github.williamandradesantana.libraryapi.model.enums.Gender;
import io.github.williamandradesantana.libraryapi.repositories.AuthorRepository;
import io.github.williamandradesantana.libraryapi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransactionsServices {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void saveBookWithPhoto() {
        // Salva o livro
        // repository.save(book)

        // pega o id do livro = livro.getId();
        // var id = book.getId();

        // salva a foto do livro -> bucket na nuvem
        // bucketService.save(book.getPhoto(), id + ".png");

        // atualizar o nome do arquivo que foi salvo
        // book.setFilePhotoName(id + ".png");
    }

    @Transactional
    public void updateWithoutUpdate() {
        bookRepository
                .findById(UUID.fromString("b6f328cb-770c-4f0f-bc55-16e56358d51f"))
                .ifPresent(b -> {
                    b.setPublishDate(LocalDate.of(2005, 4, 1));
                });
    }

    @Transactional
    public void execute() {
        // Salva o autor
        var author = new Author();
        author.setName("Author test");
        author.setNationality("Brazilian");
        author.setDateOfBirth(LocalDate.now());
        authorRepository.save(author);

        // Salva o livro
        var book = new Book();
        book.setIsbn("isbn test");
        book.setPrice(BigDecimal.valueOf(10.0));
        book.setGender(Gender.FANTASY);
        book.setTitle("Title test");
        book.setPublishDate(LocalDate.now());

        book.setAuthor(author);
        bookRepository.save(book);

        if (author.getName() == "Author test") {
            throw new RuntimeException("Rollback!");
        }
    }
}
