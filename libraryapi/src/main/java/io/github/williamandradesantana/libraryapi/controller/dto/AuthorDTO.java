package io.github.williamandradesantana.libraryapi.controller.dto;

import java.time.LocalDate;

public record AuthorDTO(String name, LocalDate dateOfBirth, String nationality) {
}
