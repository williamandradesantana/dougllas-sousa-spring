package io.github.williamandradesantana.libraryapi.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "Author")
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
}
