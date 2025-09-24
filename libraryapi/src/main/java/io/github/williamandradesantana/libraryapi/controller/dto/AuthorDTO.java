package io.github.williamandradesantana.libraryapi.controller.dto;

import io.github.williamandradesantana.libraryapi.model.Author;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
        UUID id,
        String name,
        LocalDate dateOfBirth,
        String nationality
) {
    public Author mapAuthor() {
        var author = new Author();
        author.setName(this.name);
        author.setNationality(this.nationality);
        author.setDateOfBirth(this.dateOfBirth);
        return author;
    }
}
