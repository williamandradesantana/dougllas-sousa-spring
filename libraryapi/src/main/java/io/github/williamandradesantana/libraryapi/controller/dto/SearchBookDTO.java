package io.github.williamandradesantana.libraryapi.controller.dto;

import io.github.williamandradesantana.libraryapi.model.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SearchBookDTO(
        UUID id,
        String isbn,
        String title,
        LocalDate publishDate,
        Gender gender,
        BigDecimal price,
        AuthorDTO author
) {
}
