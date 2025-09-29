package io.github.williamandradesantana.libraryapi.controller.dto;

import io.github.williamandradesantana.libraryapi.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
        UUID id,
        @NotBlank(message = "The name is required!")
        String name,
        @NotNull(message = "The date of birth is required!")
        LocalDate dateOfBirth,
        @NotBlank(message = "The nationality is required!")
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
