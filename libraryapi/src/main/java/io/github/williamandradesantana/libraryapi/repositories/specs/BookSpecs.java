package io.github.williamandradesantana.libraryapi.repositories.specs;

import io.github.williamandradesantana.libraryapi.model.Book;
import io.github.williamandradesantana.libraryapi.model.enums.Gender;
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
}
