package io.github.williamandradesantana.libraryapi.repositories;

import io.github.williamandradesantana.libraryapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    @Query("""
            select a.name
            from Author a
            where a.name like concat(:letter, '%')
            """)
    List<String> howManyAuthorsStartsWithTheLetter(@Param("letter") String letter);

    List<Author> findByNameContainingIgnoreCase(String name);
    List<Author> findByNationalityContainingIgnoreCase(String nationality);
    List<Author> findByNameContainingIgnoreCaseAndNationalityContainingIgnoreCase(String name, String nationality);

    List<Author> findByNameStartsWithIgnoreCase(String letter);
}
