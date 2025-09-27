package io.github.williamandradesantana.libraryapi.repositories;

import io.github.williamandradesantana.libraryapi.model.Author;
import io.github.williamandradesantana.libraryapi.model.Book;
import io.github.williamandradesantana.libraryapi.model.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see BookRepositoryTest
 */
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

    // JPQL -> Referência as entidades e as propriedades
    // select b.* from Book b order by b.title, b.price;
    @Query("select b from Book b order by b.title, b.price")
    List<Book> findAllBooks();

    @Query("select a from Book b join b.author a")
    List<Author> authorsWithBooks();

    @Query("select distinct b.title from Book b")
    List<String> listBookTitles();

    @Query("""
            select distinct b.gender
            from Book b
            left join b.author a
            where a.nationality = 'British'
            order by b.gender
            """)
    List<String> listGendersWithNationalityBritish();

    // Named parameters
    @Query("""
            select b
            from Book b
            where b.gender = :gender
            order by :price
            """)
    List<Book> findByGender(@Param("gender") Gender gender, @Param("price") String price);

    // Positional parameters
    @Query("""
            select b
            from Book b
            where b.gender = ?1
            order by ?2
            """)
    List<Book> findByGenderPositionalParameters(Gender gender, String price);

    @Modifying
    @Transactional
    @Query("delete from Book where gender = :gender")
    void deleteByGender(@Param("gender") Gender gender);

    @Modifying
    @Transactional
    @Query("update Book b set b.publishDate = :newDate where b.id = :id")
    void updatePublishDate(@Param("newDate") LocalDate newDate, @Param("id") UUID id);
    boolean existsByAuthor(Author author);
}
