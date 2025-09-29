package io.github.williamandradesantana.libraryapi.validator;

import io.github.williamandradesantana.libraryapi.exceptions.DuplicateRegisterException;
import io.github.williamandradesantana.libraryapi.model.Author;
import io.github.williamandradesantana.libraryapi.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorValidator {
    private final AuthorRepository authorRepository;

    public void validate(Author author) {
        if (existsRegisteredAuthor(author)) {
            throw new DuplicateRegisterException("The author already exists!");
        }
    }

    private boolean existsRegisteredAuthor(Author author) {
        return authorRepository.findByNameAndNationalityAndDateOfBirth(
                        author.getName(), author.getNationality(), author.getDateOfBirth()
        ).filter(a -> !a.getId().equals(author.getId()))
        .isPresent();
    }
}
