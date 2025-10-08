package io.github.williamandradesantana.libraryapi.repositories.specs;

import io.github.williamandradesantana.libraryapi.model.Author;
import io.github.williamandradesantana.libraryapi.model.Book;
import io.github.williamandradesantana.libraryapi.model.enums.Gender;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecs {

    public static Specification<Book> isbnEqual(String isbn) {
        // where isbn = :isbn
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    public static Specification<Book> titleEqual(String title) {
        return (root, query, cb)
                -> cb.like( cb.upper( root.get("title") ), '%' + title.toUpperCase() + '%');
    }

    public static Specification<Book> genderEqual(Gender gender) {
        return (root, query, cb) -> cb.equal(root.get("gender"), gender);
    }

    public static Specification<Book> yearOfPublicationEqual(Integer yearOfPublication) {
        // and to_char(publishDate, 'YYYY') = :yearOfPublication
        return (root, query, cb) ->
                cb.equal(
                        cb.function("to_char", String.class, root.get("publishDate"), cb.literal("YYYY")),
                        yearOfPublication.toString()
        );
    }

    public static Specification<Book> authorNameLike(String authorName) {
        return ((root, query, cb) ->
//            cb.like(cb.upper( root.join("author").get("name")), '%' + authorName.toUpperCase() + '%')
        {
            Join<Book, Author> author = root.join("author", JoinType.LEFT);
            return cb.like(cb.upper(author.get("name")), '%' + authorName.toUpperCase() + '%');
        }
        );
    }
}
