package io.github.williamandradesantana.libraryapi.repositories;

import io.github.williamandradesantana.libraryapi.model.Author;
import io.github.williamandradesantana.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    /*
        Query method
        select * from tb_books where author_id = id;
    */
    List<Book> findByAuthor(Author author);

    // select * from tb_books where isbn = isbn;
    List<Book> findByIsbn(String isbn);

    // select * from tb_books where title = title; caso seja único retorna a entidade
    List<Book> findByTitle(String title);

    // select * from tb_books where (title = ? and price = ?);
    List<Book> findByTitleAndPrice(String title, BigDecimal price);

    // select * from tb_books where (title = ? or isbn = ?);
    List<Book> findByTitleOrIsbn(String title, String isbn);

    List<Book> findByPublishDateBetween(LocalDate start, LocalDate end);
}
