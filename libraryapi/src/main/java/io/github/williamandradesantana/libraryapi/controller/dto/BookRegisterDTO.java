package io.github.williamandradesantana.libraryapi.controller.dto;

import io.github.williamandradesantana.libraryapi.model.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookRegisterDTO(
        @ISBN
        @NotBlank(message = "The isbn is required!")
        String isbn,
        @NotBlank(message = "The title is required!")
        String title,
        @NotNull(message = "The publish date is required!")
        @PastOrPresent(message = "Publish date cannot be a future date")
        LocalDate publishDate,
        Gender gender,
        BigDecimal price,
        @NotNull(message = "The author id is required!")
        UUID authorId
) {
}
