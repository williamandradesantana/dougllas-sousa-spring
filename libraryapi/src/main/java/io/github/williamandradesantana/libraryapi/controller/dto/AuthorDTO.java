package io.github.williamandradesantana.libraryapi.controller.dto;

import io.github.williamandradesantana.libraryapi.model.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(
        UUID id,
        @NotBlank(message = "The name is required!")
        @Size(min = 2, max = 100, message = "The name must be between 3 and 100 chars!")
        String name,
        @NotNull(message = "The date of birth is required!")
        @PastOrPresent(message = "Date of birth cannot be a future date")
        LocalDate dateOfBirth,
        @NotBlank(message = "The nationality is required!")
        @Size(min = 2, max = 100, message = "The name must be between 3 and 50 chars!")
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
